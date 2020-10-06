package de.urs.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSlider;
import javax.swing.JTextArea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.urs.conf.ConfigUtility;
import de.urs.data.DataList;
import de.urs.data.DataStats;
import de.urs.logic.event.Event;
import de.urs.logic.event.big.EventPandemie;
import de.urs.logic.event.big.EventProduktKnappheit;
import de.urs.logic.event.big.EventProduktUeberschuss;
import de.urs.logic.event.big.EventRohstoffKnappheit;
import de.urs.logic.event.big.EventRohstoffUeberschuss;
import de.urs.logic.event.big.EventSpekulationsfreudig;
import de.urs.logic.event.big.EventSteuerErstattung;
import de.urs.logic.event.big.EventSteuerNachzahlung;
import de.urs.logic.event.little.EventKrankheit;
import de.urs.logic.event.little.EventKuendigungArbeiter;
import de.urs.logic.event.little.EventSchwangerschaft;
import de.urs.logic.event.little.EventStimmung;

/**
 * Event Manager des Simulators
 *
 * @author urs
 *
 */
public class EventManager implements Serializable { // NOPMD by urs on 07.04.20, 09:17
    /**
     * Logger dieser Klasse
     */
    private static final Logger LOG = LogManager.getLogger(EventManager.class);

    /**
     * Serial Id
     */
    private static final long serialVersionUID = 6107267328979441226L;

    /**
     * Zeiger
     */
    private transient DataStats stats;
    /**
     * Zeiger
     */
    private transient Control controll;
    /**
     * Steuermeldungen
     */
    private transient JTextArea jta;
    /**
     * Slider zum Ein- und Verkauf
     */
    private transient JSlider slider;
    /**
     * Objekt
     */
    private transient DataList list;

    /**
     * Initalisierung des EventManagers
     *
     * @param betrieb     - Zeiger übernehmen von Spielscreen
     * @param eventscreen - Zeiger übernehmen von Spielscreen
     */
    public EventManager(final DataStats stats, final DataList list, final Control controll, final JSlider slider,
            final JTextArea jta) {
        updateZeiger(stats, list, controll, slider, jta);
        generateNextBigEvent();
        generateNextLittleEvent();
    }

    /**
     * Diese Funktion übergibt nur die neuen Zeigen. Die beim Spielladen leider
     * "null" gesetzt sind.
     *
     *
     */
    public final void updateZeiger(final DataStats stats, final DataList list, final Control controll,
            final JSlider slider, final JTextArea jta) {
        this.stats = stats;
        this.jta = jta;
        this.slider = slider;
        this.controll = controll;
        this.list = list;
    }

    /**
     * Generiert das nächste große Event zwischen 20 und 40 Phasen
     */

    public final void generateNextBigEvent() {
        stats.setNextBigEventin(stats.getPhasenzaehler()
                + ConfigUtility.zufallInt(ConfigUtility.GROSESEVENTMIN, ConfigUtility.GROSSANZAHL));
        stats.setNextEventBigKind(ConfigUtility.zufallInt(ConfigUtility.BIGRANGE, 0));

    }

    /**
     * Generiert das nächste kleine Event zwischen 8 und 20 Phasen
     */
    public final void generateNextLittleEvent() {
        stats.setNextLittleEventin(stats.getPhasenzaehler()
                + ConfigUtility.zufallInt(ConfigUtility.KLEINESEVENTMIN, ConfigUtility.KLEINANZAHL));
        stats.setNextEventLittleKind(ConfigUtility.zufallInt(ConfigUtility.LITLLERANGE, 0));
    }

    /**
     * Überprüft ob es an der Zeit ist ein Event zu starten Und wenn ein Event
     * abgelaufen ist, wird ggf. die Ordnung vorher wiederhergestellt.
     *
     *
     * @param phasezahler - der aktuellen Runde
     */
    public final void checkEvent(final int phasezahler) {
        if (phasezahler == stats.getNextBigEventin()) {
            executeBigEvent();
        } // end of if
        if (phasezahler == stats.getNextLittleEventin()) {
            executeLittleEvent();
        } // end of if
        eventrestore(phasezahler, false);

    }// end of checkEvent

    /**
     * Ändert die Stimmung aller arbeitenden Arbeiter. Je mach Art des Events +/-.
     *
     * @param event
     */
    private void changeStimmung(final Event.Types event) {
        final List<Arbeiter> liste = stats.getMitarbeiter();
        for (final Arbeiter ar : liste) {
            if (event == Event.Types.NEGATIV) {
                ar.subtrakhiereStimmung();
            } else if (event == Event.Types.POSITIV) {
                ar.addiereStimmung();
            }
        }
    }

    /**
     * Macht das Event rückgängig
     *
     * @param phasezahler
     */
    public void eventrestore(final int phasezahler, final boolean forcereset) {
        final List<Event> liste = list.getEventListe();
        final List<Event> copy = new ArrayList<>();

        if (!liste.isEmpty()) {
            // "Hartes Kopieren der Events"
            for (final Event event : liste) {
                copy.add(event);
            }
            for (final Event event : copy) {
                // Dauer undefinierte Events sind unrelevant für den weiteren Verlauf
                if (event.getDauer() == Event.UNDEF) {
                    liste.remove(event);
                    continue;
                } else {
                    if (phasezahler == event.getDauer() + event.getStartPhase() || forcereset) {
                        // Event ist abgelaufen
                        event.restore(stats);
                        liste.remove(event);
                        jta.setText("");
                    } // end of if
                } // end of if-else
            } // end of for
        }
    }

    /**
     * Führt ein großes Event durchführen.
     *
     */
    public final void executeBigEvent() { // NOPMD by urs on 07.04.20, 09:17
        LOG.debug("executeBigEvent");
        switch (stats.getNextEventBigKind()) {
        case 0:
            list.getEventListe().add(new EventSteuerErstattung(stats, jta));
            break;
        case 1:
            list.getEventListe().add(new EventSteuerNachzahlung(stats, jta));
            break;
        case 2:
            list.getEventListe().add(new EventRohstoffKnappheit(stats, jta));
            break;
        case 3:
            list.getEventListe().add(new EventRohstoffUeberschuss(stats, jta));
            break;
        case 4:
            list.getEventListe().add(new EventProduktKnappheit(stats, jta));
            break;
        case 5:
            list.getEventListe().add(new EventProduktUeberschuss(stats, jta));
            break;
        case 6:
            list.getEventListe().add(new EventSpekulationsfreudig(slider, controll, stats, jta));
            break;
        case 7:
            list.getEventListe().add(new EventPandemie(stats, jta));
            break;
        default:
            LOG.warn("Default Option @ executeBigEvent");
            break;
        }// end of switch

        // Stimmungänderung durch ein Event
        if (!list.getEventListe().isEmpty()) {
            final int anzahlEvents = list.getEventListe().size() - 1;
            changeStimmung(list.getEventListe().get(anzahlEvents).getType());
        }
        // Erst am Ende erst neues Event generieren
        generateNextBigEvent();
    }// end of executeEvent

    /**
     * Führt ein kleines Event durch
     */
    public final void executeLittleEvent() {
        LOG.debug("executeLittleEvent");
        switch (stats.getNextEventLittleKind()) {
        case 0:
            list.getEventListe().add(new EventSchwangerschaft(stats, jta));
            break;
        case 1:
            list.getEventListe().add(new EventKuendigungArbeiter(stats, jta));
            break;
        case 2:
            list.getEventListe().add(new EventKrankheit(stats, jta));
            break;
        case 3:
            list.getEventListe().add(new EventStimmung(stats, jta));
            break;
        default:
            LOG.warn("Default Option @ executeLittleEvent");
            break;
        }

        // Stimmungänderung durch ein Event
        if (!list.getEventListe().isEmpty()) {
            final int anzahlEvents = list.getEventListe().size() - 1;
            changeStimmung(list.getEventListe().get(anzahlEvents).getType());
        }
        // Nächstes Event generieren
        generateNextLittleEvent();
    }

}

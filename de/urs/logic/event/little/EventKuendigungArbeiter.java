package de.urs.logic.event.little;

import java.util.List;

import javax.swing.JTextArea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.urs.conf.ConfigUtility;
import de.urs.data.DataStats;
import de.urs.data.LitHelper;
import de.urs.data.StaticUtility;
import de.urs.gui.EventScreen;
import de.urs.logic.Arbeiter;
import de.urs.logic.event.Event;

/**
 * Arbeiter kündigt seien Job
 *
 * @author urs
 *
 */
public class EventKuendigungArbeiter implements Event {
    /**
     * Logger dieser Klasse
     */
    private static final Logger LOG = LogManager.getLogger(EventKuendigungArbeiter.class);
    /**
     * StartPhase des Events
     */
    private int startPhase;
    /**
     * Name des Events
     */
    private final String name = "Arbeiter kündigt Job";
    /**
     * Typ des Events
     */
    private final Types type = Types.NEUTRAL;
    /**
     * Dauer des Events
     */
    private final int dauer = Event.UNDEF;

    /**
     * Konstruktor des Events
     *
     * @param eventscreen
     * @param stats
     * @param meldung1
     */
    public EventKuendigungArbeiter(final DataStats stats, final JTextArea meldung1) {
        setStartPhase(stats.getPhasenzaehler());
        // Logik
        final List<Arbeiter> mitarbeiter = stats.getMitarbeiter();
        if (mitarbeiter.size() >= LitHelper.TWO) {
            final int zufall = ConfigUtility.zufallInt(mitarbeiter.size() - 2, 1);
            final Arbeiter arbeiter = mitarbeiter.get(zufall);
            mitarbeiter.remove(zufall);

            // Meldung erstellen
            final String builder = StaticUtility.getArbKuenTxt(arbeiter.getPosition(), arbeiter.getAnrede(),
                    arbeiter.getName());
            new EventScreen(builder);
            meldung1.setText(builder);
        } else {
            // Vorausetzung nicht erfüllt
            LOG.info("not executed Event: KuendigungArbeiter");
        }
    }

    /**
     * Wann wurde das Event gestartet?
     *
     * @param phasenzaehler
     */
    private void setStartPhase(final int phasenzaehler) {
        startPhase = phasenzaehler;

    }

    /**
     * Name des Events
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Positives/Neutrales/Negatives Event
     */
    @Override
    public Types getType() {
        return type;
    }

    /**
     * Dauer des Events in Phasen
     */
    @Override
    public int getDauer() {
        return dauer;
    }

    /**
     * Startphase des Event setzen
     */
    @Override
    public int getStartPhase() {
        return startPhase;
    }

    /**
     * Restore des Events
     */
    @Override
    public void restore(final DataStats stats) {
        // Nothing to do
    }

    /**
     * Arbeiter ID
     */
    @Override
    public String getWerID() {
        return null;
    }

}

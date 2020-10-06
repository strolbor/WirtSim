package de.urs.logic.event.little;

import java.util.List;

import javax.swing.JTextArea;

import de.urs.conf.ConfigUtility;
import de.urs.data.DataStats;
import de.urs.logic.Arbeiter;
import de.urs.logic.event.Event;

/**
 * Stimmung der Arbeiter verändern
 *
 * @author urs
 *
 */
public class EventStimmung implements Event {
    /**
     * StartPhase des Events
     */
    private int startPhase;
    /**
     * Name des Events
     */
    private final String name = "Änderung der Stimmung";
    /**
     * Typ des Events
     */
    private final Types type = Types.NEUTRAL;
    /**
     * Dauer des Events
     */
    private final int dauer = Event.UNDEF;

    /**
     * Konstruktor für das Event
     *
     * @param eventscreen
     * @param stats
     * @param meldung1
     */
    public EventStimmung(final DataStats stats, final JTextArea meldung1) {
        setStartPhase(stats.getPhasenzaehler());

        final List<Arbeiter> mitarbeiter = stats.getMitarbeiter();
        for (final Arbeiter arbeiter : mitarbeiter) {
            switch (ConfigUtility.zufallInt(3, 0)) {
            case 0:
                arbeiter.setArbeiterStimmung(Arbeiter.StimmungTypes.SCHLECHT);
                break;
            case 1:
                arbeiter.setArbeiterStimmung(Arbeiter.StimmungTypes.NORMAL);
                break;
            case 2:
                arbeiter.setArbeiterStimmung(Arbeiter.StimmungTypes.GUT);
                break;
            }
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

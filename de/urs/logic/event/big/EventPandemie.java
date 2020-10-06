package de.urs.logic.event.big;

import javax.swing.JTextArea;

import de.urs.data.DataStats;
import de.urs.data.StaticUtility;
import de.urs.gui.EventScreen;
import de.urs.logic.Arbeiter;
import de.urs.logic.event.Event;

/**
 * Corona Pandemie
 *
 * @author Corona
 *
 */
public class EventPandemie implements Event {
    /**
     * StartPhase des Events
     */
    private int startPhase;
    /**
     * Name des Events
     */
    private final String name = "Corona Pandemie";
    /**
     * Typ des Events
     */
    private final Types type = Types.NEGATIV;

    /**
     * Dauer des Events
     */
    private final int dauer = 29;

    /**
     * Initalisieren
     *
     * @param stats
     * @param meldung1
     */
    public EventPandemie(final DataStats stats, final JTextArea meldung1) {
        // wichtige Objekte speichern
        setStartPhase(stats.getPhasenzaehler());
        final String a = StaticUtility.PANDEMIE;
        new EventScreen(a);
        meldung1.setText(a);
        for (final Arbeiter ar : stats.getMitarbeiter()) {
            ar.setArbeiterStimmung(Arbeiter.StimmungTypes.KRANK);
        }
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
     * Dauer des Events in Phasen
     */
    @Override
    public int getStartPhase() {
        return startPhase;
    }

    /**
     * Startphase des Event setzen
     */
    private void setStartPhase(final int phasenzaehler) {
        startPhase = phasenzaehler;

    }

    /**
     * Restore des Events.
     */
    @Override
    public void restore(final DataStats stats) {
        for (final Arbeiter ar : stats.getMitarbeiter()) {
            ar.setArbeiterStimmung(Arbeiter.StimmungTypes.SCHLECHT);
        }

    }

    /**
     * Arbeiter ID: Zutreffend, aber alle Arbeiter sind betroffen
     *
     * @return null
     */
    @Override
    public String getWerID() {
        return null;
    }

}

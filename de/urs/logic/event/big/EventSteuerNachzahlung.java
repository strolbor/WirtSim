package de.urs.logic.event.big;

import javax.swing.JTextArea;

import de.urs.conf.ConfigUtility;
import de.urs.data.DataStats;
import de.urs.data.StaticUtility;
import de.urs.gui.EventScreen;
import de.urs.logic.event.Event;

/**
 * Steuernachzahlung
 *
 * @author urs
 *
 */
public class EventSteuerNachzahlung implements Event {
    /**
     * StartPhase des Events
     */
    private int startPhase;
    /**
     * Name des Events
     */
    private final String name = "Steuernachzahlung";
    /**
     * Typ des Events
     */
    private final Types type = Types.NEUTRAL;
    /**
     * Dauer des Events
     */
    private final int dauer = Event.UNDEF;

    /**
     * Initalisieren
     *
     * @param eventscreen
     * @param stats
     * @param jta
     */
    public EventSteuerNachzahlung(final DataStats stats, final JTextArea jta) {
        setStartPhase(stats.getPhasenzaehler());
        final double steuer = ConfigUtility.zufallDouble(100, 10);
        final String builder = StaticUtility.getSteuerNachzahlng(steuer);
        new EventScreen(builder);
        jta.setText(builder);
        stats.removeKapital(steuer);
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
     * Restore des Events. Nothing to do.
     */
    @Override
    public void restore(final DataStats stats) {
        // Nothing to do
    }

    /**
     * Arbeiter ID: nicht zutreffend
     *
     * @return null
     */
    @Override
    public String getWerID() {
        return null;
    }

}

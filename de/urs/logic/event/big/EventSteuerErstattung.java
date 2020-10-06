package de.urs.logic.event.big;

import javax.swing.JTextArea;

import de.urs.conf.ConfigUtility;
import de.urs.data.DataStats;
import de.urs.data.StaticUtility;
import de.urs.gui.EventScreen;
import de.urs.logic.event.Event;

/**
 * Steuererstattung
 *
 * @author urs
 *
 */
public class EventSteuerErstattung implements Event {
    /**
     * StartPhase des Events
     */
    private int startPhase;
    /**
     * Name des Events
     */
    private final String name = "Steuerrückerstattung";
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
    public EventSteuerErstattung(final DataStats stats, final JTextArea jta) {
        setStartPhase(stats.getPhasenzaehler());
        final double erstattung = ConfigUtility.zufallDouble(100, 1);
        final String builder = StaticUtility.getSteuerErt(erstattung);
        new EventScreen(builder);
        jta.setText(builder);
        stats.addKapital(erstattung);
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
     * Restore des Events. Nothing to do
     */
    @Override
    public void restore(final DataStats stats) {
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

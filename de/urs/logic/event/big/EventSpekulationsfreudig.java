package de.urs.logic.event.big;

import javax.swing.JSlider;
import javax.swing.JTextArea;

import de.urs.data.DataStats;
import de.urs.data.StaticUtility;
import de.urs.gui.EventScreen;
import de.urs.logic.Control;
import de.urs.logic.event.Event;

/**
 * Event Spekulationsfreudig
 *
 * @author urs
 *
 */
public class EventSpekulationsfreudig implements Event {
    /**
     * StartPhase des Events
     */
    private int startPhase;
    /**
     * Name des Events
     */
    private final String name = "Spekulationsfreudig";
    /**
     * Typ des Events
     */
    private final Types type = Types.POSITIV;

    /**
     * Dauer des Events
     */
    private final int dauer = 8;

    /**
     * Initalisieren
     * 
     * @param slider
     * @param controll
     * @param stats
     * @param jta
     */
    public EventSpekulationsfreudig(final JSlider slider, final Control controll, final DataStats stats,
            final JTextArea jta) {
        setStartPhase(stats.getPhasenzaehler());
        final String builder = StaticUtility.SPEKULATION;
        new EventScreen(builder);
        jta.setText(builder);
        stats.setEventVorteil(5);
        if (stats.isVerkaufPhase()) {
            slider.setMaximum(stats.maximumEinkaufs());
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
     * Restore des Events
     */
    @Override
    public void restore(final DataStats stats) {
        stats.setEventVorteil(0);
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

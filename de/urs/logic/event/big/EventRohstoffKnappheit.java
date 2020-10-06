package de.urs.logic.event.big;

import javax.swing.JTextArea;

import de.urs.conf.ConfigUtility;
import de.urs.data.DataStats;
import de.urs.data.StaticUtility;
import de.urs.gui.EventScreen;
import de.urs.logic.event.Event;

/**
 * Rohstoff Knappheit
 *
 * @author urs
 *
 */
public class EventRohstoffKnappheit implements Event {
    /**
     * StartPhase des Events
     */
    private int startPhase;
    /**
     * Name des Events
     */
    private final String name = "Rohstoff Knappheit";
    /**
     * Typ des Events
     */
    private final Types type = Types.NEGATIV;

    /**
     * Dauer des Events
     */
    private final int dauer = 10;

    /**
     * Initalisieren
     *
     * @param stats
     * @param meldung1
     */
    public EventRohstoffKnappheit(final DataStats stats, final JTextArea meldung1) {
        setStartPhase(stats.getPhasenzaehler());
        final String builder = StaticUtility.ROHSTOFFKNAPP;
        new EventScreen(builder);
        meldung1.setText(builder);
        ConfigUtility.setPreisspaRoh(10, 18);
        stats.setEventVorteil(-1);
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
        ConfigUtility.setPreisspaPro(ConfigUtility.UNTERROH, ConfigUtility.ABGEDECKTROH);
        stats.setEventVorteil(0);
    }

    /**
     * Arbeiter ID: Hier nicht zutreffend
     *
     * @return null
     */
    @Override
    public String getWerID() {
        // TODO Auto-generated method stub
        return null;
    }
}

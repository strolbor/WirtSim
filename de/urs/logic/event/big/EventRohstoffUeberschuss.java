package de.urs.logic.event.big;

import javax.swing.JTextArea;

import de.urs.conf.ConfigUtility;
import de.urs.data.DataStats;
import de.urs.data.StaticUtility;
import de.urs.gui.EventScreen;
import de.urs.logic.event.Event;

/**
 * Event Rohstoff Ueberschuss
 *
 * @author urs
 *
 */
public class EventRohstoffUeberschuss implements Event {
    /**
     * StartPhase des Events
     */
    private int startPhase;
    /**
     * Name des Events
     */
    private final String name = "Rohstoff Überschuss";
    /**
     * Typ des Events
     */
    private final Types type = Types.POSITIV;
    /**
     * Dauer des Events
     */
    private final int dauer = 10;

    /**
     * Initalisieren
     *
     * @param stats
     * @param jta
     */
    public EventRohstoffUeberschuss(final DataStats stats, final JTextArea jta) {
        setStartPhase(stats.getPhasenzaehler());
        final String builder = StaticUtility.ROHSTOFFUEBER;
        new EventScreen(builder);
        jta.setText(builder);
        ConfigUtility.setPreisspaRoh(1, 5);
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
    }

    /**
     * Arbeiter ID: hier nicht zutreffend
     *
     * @return null
     */
    @Override
    public String getWerID() {
        return null;
    }

}

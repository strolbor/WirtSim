package de.urs.logic.event.little;

import javax.swing.JTextArea;

import de.urs.conf.ConfigUtility;
import de.urs.data.DataStats;
import de.urs.data.StaticUtility;
import de.urs.gui.EventScreen;
import de.urs.logic.Arbeiter;
import de.urs.logic.event.Event;

/**
 * Ein Arbeiter wird krank.
 *
 * @author urs
 *
 */
public class EventKrankheit implements Event {
    /**
     * StartPhase des Events
     */
    private int startPhase;
    /**
     * Name des Events
     */
    private final String name = "Krankheit";
    /**
     * Typ des Events
     */
    private final Types type = Types.NEUTRAL;
    /**
     * Dauer des Events
     */
    private final int dauer = 8;
    /**
     * Betroffener Arbeiter
     */
    private String werID;
    /**
     * Miniumanzahl der Arbeiter für dieses Event
     */
    private transient int mininum = 2;

    /**
     * Konstruktor des Events
     *
     * @param es
     * @param stats
     * @param meldung1
     */
    public EventKrankheit(final DataStats stats, final JTextArea meldung1) {
        setStartPhase(stats.getPhasenzaehler());
        // Logik: Arbeiter finden
        if (stats.getMitarbeiter().size() > mininum) {
            // zufällig auspicken
            final int zufall = ConfigUtility.zufallInt(stats.getMitarbeiter().size() - 1, 1);
            final Arbeiter arr = stats.getMitarbeiter().get(zufall);
            werID = arr.getId();
            arr.setArbeiterStimmung(Arbeiter.StimmungTypes.NICHTARBEITEND);

            // Meldung erstellen
            meldung1.setText(StaticUtility.getKRANKHEITTEXT(arr.getAnrede(), arr.getName()));
            new EventScreen(StaticUtility.getKRANKHEITTEXT(arr.getAnrede(), arr.getName()));
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
        if (getWerID() == null) {
            return;
        }
        for (final Arbeiter ar : stats.getMitarbeiter()) {
            if (getWerID().equals(ar.getId())) {
                ar.setArbeiterStimmung(Arbeiter.StimmungTypes.SCHLECHT);
            }
        }
    }

    /**
     * Arbeiter ID
     */
    @Override
    public String getWerID() {
        return werID;
    }

}

package de.urs.logic.event.little;

import java.util.List;

import javax.swing.JTextArea;

import de.urs.data.DataStats;
import de.urs.data.NamenHelper;
import de.urs.data.StaticUtility;
import de.urs.gui.EventScreen;
import de.urs.logic.Arbeiter;
import de.urs.logic.event.Event;

/**
 * Schwangerschaft-Event
 *
 * @author urs
 *
 */
public class EventSchwangerschaft implements Event {
    /**
     * StartPhase des Events
     */
    private int startPhase;
    /**
     * Name des Events
     */
    private final String name = "Schwangerschaft";
    /**
     * Typ des Events
     */
    private final Types type = Types.NEUTRAL;
    /**
     * Dauer des Events
     */
    private final int dauer = 14;
    /**
     * Betroffener Arbeiter
     */
    private String werID;

    /**
     * Konstruktor des Events
     *
     * @param eventscreen
     * @param stats
     * @param meldung1
     */
    public EventSchwangerschaft(final DataStats stats, final JTextArea meldung1) {
        setStartPhase(stats.getPhasenzaehler());

        // LogikList<A> Frauen erkennung
        final List<Arbeiter> mitarbeiter = stats.getMitarbeiter();
        for (final Arbeiter arbeiter : mitarbeiter) {
            if (arbeiter.getPosition().equals(NamenHelper.getPosition(1)) && arbeiter.getProduzieren() != 0) {
                arbeiter.setArbeiterStimmung(Arbeiter.StimmungTypes.SCHWANGER);
                werID = arbeiter.getId();

                final String builder = StaticUtility.getSchwanger(arbeiter.getName());
                new EventScreen(builder);
                meldung1.setText(builder);
                return;
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
    public void restore(final DataStats stat) {
        final List<Arbeiter> list = stat.getMitarbeiter();
        for (final Arbeiter ar : list) {
            if (getWerID().equals(ar.getId())) {
                ar.setArbeiterStimmung(Arbeiter.StimmungTypes.GUT);
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

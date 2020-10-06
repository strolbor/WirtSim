package de.urs.logic.event;

import de.urs.data.DataStats;

/**
 * Interface fuer die Events
 *
 * @author urs
 *
 */
public interface Event {
    /**
     * Variable für die Dauer, die undefiniert ist
     */
    int UNDEF = -1;

    /**
     * Arten von Events
     *
     * @author urs
     *
     */
    enum Types {
        POSITIV, NEUTRAL, NEGATIV
    }

    /**
     * Name des Events
     *
     * @return Name
     */
    String getName();

    /**
     * Welchen Einfluss hat es auf die Arbeiter
     *
     * @return Einfluss auf Arbeiter
     */
    Types getType();

    /**
     * Dauer des Events.
     *
     * @return Dauer des Events
     */
    int getDauer();

    /**
     * Gibt die Startphase zurück
     *
     * @return Phase
     */
    int getStartPhase();

    /**
     * Macht das Event Rückgängig
     *
     * @param stats
     */
    void restore(DataStats stats);

    /**
     * Eventuelle Speicherung der Arbeiter ID
     *
     * @return ID des Arbeiters
     */
    String getWerID();

}

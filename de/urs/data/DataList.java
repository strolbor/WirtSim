package de.urs.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.urs.logic.event.Event;

/**
 * Datenliste des Spiels
 *
 * @author urs
 *
 */
public class DataList implements Serializable { // NOPMD by urs on 06.04.20, 14:10
    /**
     * Serial ID
     */
    private static final long serialVersionUID = -2029239149449434714L;
    /**
     * Eventliste als ArrayList.
     */
    private List<Event> eventListe = new ArrayList<>();

    /**
     * Gibt die Eventliste zurück. Type: List<Event>
     *
     * @return Eventliste
     */
    public List<Event> getEventListe() {
        return eventListe;
    }

    /**
     * Eine Funktion, die verhindert, das Save Actions sie zu final macht
     *
     * @param eventListe
     */
    public void setEventListe(final List<Event> eventListe) {
        this.eventListe = eventListe;
    }

}

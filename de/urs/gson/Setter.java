package de.urs.gson;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.urs.data.DataID;
import de.urs.data.DataList;
import de.urs.data.DataStats;
import de.urs.logic.event.Event;

/**
 * Speicherung der Daten
 *
 * @author urs
 *
 */
public final class Setter { // NOPMD by urs on 06.04.20, 14:28

    /**
     * Konvertierung der Daten
     *
     * @param DataStats
     * @return JSON String
     */
    public String convertData(final DataStats stlds) {
        final Gson gson = new Gson();
        return gson.toJson(stlds);

    }

    /**
     * Convert die Eventliste in JSON Format
     *
     * @param DataList
     * @return JSON String
     */
    public String convertList(final DataList ueberListe) {
        // GSON erstellen
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Event.class, new InterfaceAdapter());
        builder.excludeFieldsWithModifiers(Modifier.TRANSIENT);
        final Gson gson = builder.create();
        // Convertieren
        final List<Event> list = ueberListe.getEventListe();
        final Type type = GsonUtility.createType(list, Event.class);
        return gson.toJson(list, type); // NOPMD by urs on 06.04.20, 14:29
    }

    /**
     * Convert die ID in JSON
     *
     * @param DataID
     * @return JSON String
     */
    public String convertID(final DataID idHelper) {
        return new Gson().toJson(idHelper);
    }

}

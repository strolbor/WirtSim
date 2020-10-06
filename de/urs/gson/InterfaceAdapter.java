package de.urs.gson;

import java.lang.reflect.Type;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import de.urs.logic.event.Event;

/**
 * Adapter für den Import und Export der Eventliste
 *
 * @author urs
 * @contributor kay
 *
 */
public class InterfaceAdapter implements JsonDeserializer<Event>, JsonSerializer<Event> { // NOPMD by urs on 07.04.20,
                                                                                          // 08:02
    /**
     * Logger für die Klasse ArbeiterGUI
     */
    private static final Logger LOG = LogManager.getLogger(InterfaceAdapter.class);
    /**
     * Eventnamen geben
     */
    private static final String CLASSNAME = "EVENTNAME";
    /**
     * Eventdaten geben
     */
    private static final String DATA = "DATA";

    /**
     * GSON/JSON Serialisation
     */
    @Override
    public JsonElement serialize(final Event event, final Type arg1, final JsonSerializationContext jSerCon) {
        final JsonObject jsonObject = new JsonObject();
        final String className = event.getClass().getName(); // NOPMD by urs on 07.04.20, 08:06
        jsonObject.addProperty(CLASSNAME, className);
        jsonObject.add(DATA, jSerCon.serialize(event));
        return jsonObject;
    }

    /**
     * GSON/JSON deserialisieren
     */
    @Override
    public Event deserialize(final JsonElement jsonElement, final Type arg1, final JsonDeserializationContext jDEcon)
            throws JsonParseException { // NOPMD by urs on 07.04.20, 07:57
        final JsonObject jsonObject = jsonElement.getAsJsonObject();
        final JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME); // NOPMD by urs on 07.04.20, 08:06
        final String className = prim.getAsString(); // NOPMD by urs on 07.04.20, 08:06
        final Class<?> klass = getObjectClass(className);
        return jDEcon.deserialize(jsonObject.get(DATA), klass); // NOPMD by urs on 07.04.20, 08:06
    }

    /**
     * Hilfsfunktion Objekt Klasse zurückgeben
     *
     * @param className
     * @return (Class)
     * @throws ClassNotFoundException
     */
    public Class<?> getObjectClass(final String className) {
        try {
            return Class.forName(className);
        } catch (final ClassNotFoundException e) {
            LOG.error("ClassNotFoundException @ InterfaceAdapter2: ", e);
            throw new JsonParseException(e.getMessage()); // NOPMD by urs on 07.04.20, 08:02
        }
    }
}

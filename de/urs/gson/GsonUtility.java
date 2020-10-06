package de.urs.gson;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

/**
 * Hilfs Klasse für Getter und Setter.
 *
 * @author urs
 *
 */
public class GsonUtility { // NOPMD by urs on 07.04.20, 08:06
    /**
     * Erstellt den Typen der Liste
     *
     * @param <T>      Generic
     * @param liste    Liste
     * @param ojbClass Objektklasse
     * @return TypeToken
     */
    protected static <T> Type createType(final List<T> liste, final Class<T> ojbClass) {
        return TypeToken.getParameterized(liste.getClass(), ojbClass).getType(); // NOPMD by urs on 07.04.20, 08:04
    }
}

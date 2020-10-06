package de.urs.objects;

/**
 * Eigene Exception für keinen SQL-Eintrag in der Datenbank
 * 
 * @author urs
 *
 */
public class NoEntryException extends Exception {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = -3813678654993887055L;

    /**
     * Konstruktor
     */
    public NoEntryException() {
        super("Kein Eintrag in der Datenbank Fehler");
    }

}

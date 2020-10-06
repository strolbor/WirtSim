package de.urs.conf;

/**
 * Save Config Klasse
 *
 * @author urs
 *
 */
public final class SaveUtility { // NOPMD by urs on 08.04.20, 07:51
    /* Methoden */
    /**
     *
     * @return Name der Spieldatei
     */
    public static String getLName() {
        return "game.wsl";
    }

    /**
     *
     * @return Name der Spieldatei
     */
    public static String getKName() {
        return "game.wsj";
    }

    /**
     *
     * @return Name der ID Speicherungsid
     */
    public static String getIDName() {
        return "ID.wsi";
    }

    /**
     * @return Name der ID-Log Datei
     */
    public static String getIDLogName() {
        return "ID.log";
    }

    /**
     * Konstruktor, privat
     */
    private SaveUtility() {

    }

}
package de.urs.data;

import java.io.Serializable;

import de.urs.conf.ConfigUtility;

/**
 * ID Berathalter
 *
 * @author urs
 *
 */
public final class DataID implements Serializable { // NOPMD by urs on 07.04.20, 07:54
    /**
     * Serial ID
     */
    private static final long serialVersionUID = 3553170448755235214L;

    // Optionas
    // ------------------------------------------------------//
    /**
     * Id holen
     */
    private String id; // NOPMD by urs on 06.04.20, 14:10

    // ------------------------------------------------------//
    /**
     * Game ID holen
     *
     * @return Id
     */
    public String getID() {
        return id;
    }

    /**
     * Game ID setzen
     *
     * @param iD
     */
    public void setID(final String iD) { // NOPMD by urs on 06.04.20, 14:10
        id = iD;
    }

    /**
     * Game ID generieren
     */
    public void generateID() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= 7; i++) {
            // Will eine ID mit 14 Zffern haben
            final int buch1 = ConfigUtility.zufallInt(25, 65);
            final int buch2 = ConfigUtility.zufallInt(25, 97);
            builder.append((char) buch1).append((char) buch2);
        }
        setID(builder.toString());
    }

    /**
     * Arbeiter ID generieren
     */
    public static String generateArbeiterID() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= 14; i++) {
            // Will eine ID mit 14 Zffern haben
            final int buch1 = ConfigUtility.zufallInt(25, 65);
            builder.append((char) buch1);
        }
        return builder.toString();
    }
}

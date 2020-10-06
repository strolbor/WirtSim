package de.urs.objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import de.urs.conf.SaveUtility;

/**
 * Schreiber der Datei
 *
 * @author urs
 *
 */
public final class IDHelper { // NOPMD by urs on 06.04.20, 13:55

    /**
     * Schreibt eine Datei mit den übergebenen Namen und der übergebenen Lines
     * (String[]). AppendMode
     *
     * @param name - Name der Datei
     * @param line - Inhalt der Datei
     * @throws IOException
     */
    public static void write(final String name, final String line, final boolean appendmode) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new FileWriter(name, appendmode)); // NOPMD by urs on 06.04.20,
        writer.write(line);
        if (appendmode) {
            writer.newLine();
        }
        writer.close();
    }

    /**
     * Liest die Spieldatei aus.
     *
     * @throws IOException
     */
    public String readID() throws IOException {
        final BufferedReader reader = new BufferedReader(new FileReader(SaveUtility.getIDName())); // NOPMD by urs on
                                                                                                   // 06.04.20, 13:54
        final String intext = reader.readLine();
        reader.close();
        return intext;
    } // end of while

}
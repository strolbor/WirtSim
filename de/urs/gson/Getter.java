package de.urs.gson;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import de.urs.conf.SaveUtility;
import de.urs.data.DataID;
import de.urs.data.DataList;
import de.urs.data.DataStats;
import de.urs.data.LitHelper;
import de.urs.data.StaticUtility;
import de.urs.logic.event.Event;
import de.urs.objects.IDHelper;
import de.urs.objects.NoEntryException;
import de.urs.sql.SqlApi;

/**
 * Methoden, um den Spielstand wiederherzustellen
 *
 * @author urs
 *
 */
public final class Getter {
    /**
     * eingelesesene Stats Datei
     */
    private transient DataStats stats;
    /**
     * eingelesesene Eventlist Datei
     */
    private transient final DataList list;
    /**
     * eingelesesene ID Datei
     */
    private transient DataID dataID;
    /**
     * ID des Spielers abrufen
     */
    private String gameid;

    /**
     * User fragen, mit welcher ID er weiterspielen will
     *
     * @throws NoEntryException
     * @throws IOException
     * @throws SQLException
     */
    public Getter() throws NoEntryException, IOException, SQLException {
        list = new DataList();
        final File file = new File(SaveUtility.getIDName());
        final IDHelper read = new IDHelper();
        int userwahl = LitHelper.ZERO;
        if (file.exists()) {
            // ID ist vorhanden
            final String jsonString = read.readID();
            dataID = new Gson().fromJson(jsonString, DataID.class);
            setGameid(dataID.getID());
            userwahl = JOptionPane.showConfirmDialog(null, StaticUtility.GETTERWEITER, StaticUtility.INPUT,
                    JOptionPane.YES_NO_OPTION);
        } else {
            // ID nicht vorhanden
            userwahl = LitHelper.ONE;
        }

        if (userwahl == LitHelper.ONE) {
            // Spieler will eine andere ID eingeben
            final String code = JOptionPane.showInputDialog(StaticUtility.GETTERIDINPUT);
            setGameid(code);
            dataID = new DataID();
            dataID.setID(code);
        }
        final SqlApi sapi = new SqlApi();
        sapi.querySpieldaten(getGameid());
        queryGameData(sapi);
        queryListData(sapi);

    }

    /**
     * Methode um die Spieldaten, außer die Eventliste, aus der Datenbank zu
     * erhalten.
     *
     * @param sapi - SQL API, mit der die Daten geholten wurden sind
     */
    private void queryGameData(final SqlApi sapi) {
        final Gson gson = new Gson();
        final Type type = new TypeToken<DataStats>() {
        }.getType();
        stats = gson.fromJson(sapi.getSpieldaten(), type);
    }

    /**
     * Methode um die Spieldaten, die Eventliste, aus der Datenbank zu erhalten.
     *
     * @param sapi - SQL API, mit der die Daten geholten wurden sind
     */
    private void queryListData(final SqlApi sapi) {
        // GSON erstellen
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Event.class, new InterfaceAdapter());
        final Gson gson = builder.create();

        // Liste vorbereiten
        List<Event> eventlsite = new ArrayList<>();
        final Type typeEventList = GsonUtility.createType(eventlsite, Event.class);

        // Query
        eventlsite = gson.fromJson(sapi.getListdaten(), typeEventList); // NOPMD by urs on 07.04.20, 08:06
        list.setEventListe(eventlsite);
    }

    /**
     * Gibt den gesicherten Stats
     *
     * @return gibt die ausgelesene Stats zurück
     */
    public DataStats getStats() {
        return stats;
    }

    /**
     * Gibt den gesicherten Listen
     *
     * @return gibt die ausgelesene Events zurück
     */
    public DataList getList() {
        return list;
    }

    /**
     *
     * @return Spiel ID
     */
    public String getGameid() {
        return gameid;
    }

    /**
     *
     * @param gameid - Spiel ID
     */
    private void setGameid(final String gameid) {
        this.gameid = gameid;
    }

    /**
     * Gibt die ausgelesene ID des Spielers zurück.
     *
     * @return ID
     */
    public DataID getIDhelper() {
        return dataID;
    }

}

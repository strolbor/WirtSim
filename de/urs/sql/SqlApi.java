package de.urs.sql;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.urs.objects.NoEntryException;

/**
 * Klasse zum SQL connecten
 *
 * @author urs
 *
 */
public class SqlApi { // NOPMD by urs on 06.04.20, 14:02
    /**
     * Logger dieser Klasse
     */
    private static final Logger LOG = LogManager.getLogger(SqlApi.class);

    /**
     * Passwort
     */
    private static final String PASSWORD = "PASSWORD";
    /**
     * Host
     */
    private static final String HOST = "HOST.example";
    /**
     * Datenbankname
     */
    private static final String DBNAME = "DB-Name";
    /**
     * Datenbankuser
     */
    private static final String USER = "DB-User";

    /**
     * Abfrage Ergebnis speichern
     */
    private transient ResultSet resultSet;
    /**
     * Die Verbindung zur Datenbank
     */
    private transient Connection connection;
    /**
     * Das Statement um ein SQL-Befehl auszuführen
     */
    private transient Statement statemant;
    /**
     * URL zur Datenbank
     */
    private transient final String url = "jdbc:mysql://" + HOST + "/" + DBNAME + "?user=" + USER + "&password="
            + PASSWORD;

    /**
     * Spieldaten Speicher
     */
    private transient String spieldaten;
    /**
     * Eventdaten Speicher
     */
    private transient String listdaten;

    /**
     * Spielstand sichern
     *
     * @param spieldaten des Spielers
     * @param eventdaten des Spielers
     * @param code       des Spielers
     * @throws SQLException
     */
    public void querySaveSpieldaten(final String spieldaten, final String eventdaten, final String code)
            throws SQLException {
        try {
            connection = DriverManager.getConnection(url);
            statemant = connection.createStatement();
            String sql;
            int zahler = 0;
            // Überprüfen, ob es schon eine Game-ID in der Tabelle vorhanden ist
            final String abs = "SELECT * from Spieldaten where ID = '" + code + "'";
            resultSet = statemant.executeQuery(abs);
            while (resultSet.next()) {
                zahler += 1;
            }
            /**
             * Wenn es nicht vorhanden ist, kann das resultSet nicht nexten und 0 bleibt
             * erhalten
             */
            final String dateformated = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY).format(new Date());
            if (zahler == 0) {
                // Wenn nichts vorhanden ist, muss man die ID und die Daten eintragen
                sql = "INSERT INTO Spieldaten values ('" + code + "','" + spieldaten + "','" + eventdaten + "', '" // NOPMD
                                                                                                                   // by
                                                                                                                   // urs
                                                                                                                   // on
                                                                                                                   // 07.04.20,
                                                                                                                   // 07:42
                        + dateformated + "');";
            } else {
                // Wenn die ID schon vorhanden ist, muss man es updaten
                sql = "UPDATE `Spieldaten` SET `Spieldaten`='" + spieldaten + "',`Date`='" + dateformated + "', "
                        + "`Listdaten`='" + eventdaten + "' where ID = '" + code + "'";
            }
            insertQuery(sql);
        } catch (final SQLException e) {
            LOG.fatal("SQLException @ querySaveSpieldaten: ", e);
            throw e;
        } finally {
            close();
        }
    }

    /**
     * Spielstand laden
     *
     * @param code des Spielers
     * @throws NoEntryException
     * @throws SQLException
     *
     */
    public void querySpieldaten(final String code) throws NoEntryException, SQLException {
        try {
            connection = DriverManager.getConnection(url);
            statemant = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statemant
                    .executeQuery("SELECT Spieldaten, Listdaten from Spieldaten where ID = '" + code + "'");
            int zaH = 0;
            while (resultSet.next()) {
                spieldaten = resultSet.getString("Spieldaten");
                listdaten = resultSet.getString("Listdaten");
                zaH += 1;
            }
            if (zaH == 0) {
                throw new NoEntryException();
            }
        } catch (final SQLException e) {
            LOG.fatal("SQLException @ querySpieldaten: ", e);
            throw e;
        } finally {
            close();
        }

    }

    /**
     * Score Speichern bei der Einwilligung
     *
     * @param name  des Spielers
     * @param score des Spielers
     * @param code  des Spielers
     * @throws SQLException
     */
    public void querySaveScore(final String name, final BigInteger score, final String code) throws SQLException {
        String commandSQL = ""; // NOPMD by urs on 07.04.20, 07:42
        try {
            final String dateformated = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY).format(new Date());
            commandSQL = "INSERT INTO `Score`(`Name`, `Score`, `Date`) VALUES ('" + name + "','" + score.toString()
                    + "','" + dateformated + "')";
            if (insertQuery(commandSQL)) {
                // Spielstand löschen
                deleteSpielstand(code);
            } else {
                JOptionPane.showMessageDialog(null, "Score konnte nicht gespeichert werden.");
            }
        } catch (final SQLException e) {
            LOG.fatal("SQLException @ querySaveScore: " + commandSQL, e);
            throw e;
        } finally {
            close();
        }

    }

    /**
     * Spielstand löschen
     *
     * @param Game-ID (code)
     */
    public void deleteSpielstand(final String code) {
        final String commandSQL = "DELETE FROM `Spieldaten` WHERE ID = '" + code + "';";
        try {
            insertQuery(commandSQL);
        } catch (final SQLException e) {
            LOG.error("SQLException @ deleteSpielstand: ", e);
        }
    }

    /**
     * richtige SQL Query zum einfügen/löschen von Daten, bei denen keine Antwort
     * erwartet wird.
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    protected boolean insertQuery(final String sql) throws SQLException {
        boolean exec = false; // NOPMD by urs on 07.04.20, 07:42
        try {
            connection = DriverManager.getConnection(url);
            statemant = connection.createStatement();
            if (!statemant.execute(sql)) {
                exec = true;
            }
        } catch (final SQLException e) {
            LOG.fatal("SQLException @ realSQLQuery: " + sql, e);
        } finally {
            close();
        }
        if (exec) { // NOPMD by urs on 07.04.20, 07:42
            return true; // NOPMD by urs on 07.04.20, 07:42
        }
        return false;
    }

    /**
     * Spieldatenstring zurückgeben
     *
     * @return spieldaten
     */
    public String getSpieldaten() {
        return spieldaten;
    }

    /**
     * Listendaten-String zurückgeben
     *
     * @return listdaten
     */
    public String getListdaten() {
        return listdaten;
    }

    /**
     * Verbindung zur Datenbank eleminieren
     *
     * @throws SQLException
     */
    private void close() throws SQLException {

        try {
            if (statemant != null) {
                statemant.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (final SQLException e) {
            LOG.fatal("SQLException @ close(): ", e);
            throw e;
        }
    }
}

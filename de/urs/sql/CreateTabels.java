package de.urs.sql;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Klasse zur Erstellen der DB
 *
 * @author urs
 *
 */
public class CreateTabels {
    /**
     * Logger dieser Klasse
     */
    private static final Logger LOG = LogManager.getLogger(CreateTabels.class);
    /**
     * String zur DB Erstellung
     */
    private static final String DBERSTELLEN1 = "CREATE TABLE if not exists Spieldaten(ID varchar(25), Spieldaten Longtext, Listdaten Longtext,Date date, PRIMARY KEY(ID));";
    /**
     * String zur DB Erstellung
     */
    private static final String DBERSTELLEN2 = "CREATE TABLE if not exists Score(ID int(32) AUTO_INCREMENT, Name Longtext, Score int(99),Date date, PRIMARY KEY(ID));";
    @SuppressWarnings("unused")
    private static String drop1 = "DROP TABLE 'Spieldaten';";
    @SuppressWarnings("unused")
    private static String drop2 = "DROP TABLE 'Score';";

    /**
     * Starter der Tabellen erstellung
     * 
     * @param args
     */
    public static void main(final String[] args) {
        LOG.info("Start");
        final SqlApi api = new SqlApi();
        try {
            api.insertQuery(DBERSTELLEN1);
            api.insertQuery(DBERSTELLEN2);
        } catch (final SQLException e) {
            LOG.fatal(e);
        }
        LOG.info("ENDE");

    }

}

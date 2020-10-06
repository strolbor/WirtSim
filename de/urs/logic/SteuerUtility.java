package de.urs.logic;

import java.io.Serializable;

import javax.swing.JTextArea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.urs.conf.ConfigUtility;
import de.urs.data.DataStats;
import de.urs.data.StaticUtility;
import de.urs.main.StarterUtility;

/**
 * Steuerverwaltung des Simulators
 *
 * @author urs
 *
 */
public class SteuerUtility implements Serializable { // NOPMD by urs on 08.04.20, 07:30
    /**
     * Logger dieser Klasse
     */
    private static final Logger LOG = LogManager.getLogger(StarterUtility.class);

    /**
     * Serial ID
     */
    private static final long serialVersionUID = 423831523027037092L;
    /**
     * Umsatzsteuersatz (linear)
     */
    public static final double UMSATZSTEUER = 0.25;

    /**
     * Gewebersteuersatz (linear)
     */
    public static final double GEWERBESTEUER = 0.1;

    /**
     * Körperschaftssteuer Für später eine Funktion einprogrammieren.
     * (logarithmisch)
     */
    public static final double KOERPERSTEUER = 2.4;

    /**
     * Berechnung der Steuern
     *
     * @param jtaSteuer
     */
    public static final void calculateTaxes(final JTextArea jtaSteuer, final DataStats stats) {
        LOG.debug("calculateTaxes");
        final double umsatz = ConfigUtility.roundbetter(stats.getUmsatz() * UMSATZSTEUER);
        final double koerpersteuer = ConfigUtility.roundbetter(stats.getMitarbeiter().size() * KOERPERSTEUER); // NOPMD by urs on 08.04.20, 07:31
        final double gewebersteuer = ConfigUtility.roundbetter(stats.getUmsatz() * GEWERBESTEUER);
        jtaSteuer.setText(StaticUtility.getSTEUERdata(umsatz, koerpersteuer, gewebersteuer));
        stats.removeKapital(umsatz + gewebersteuer + koerpersteuer);
        stats.resetSteuerUmsatz();
    }

}

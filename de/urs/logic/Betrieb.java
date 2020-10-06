package de.urs.logic;

import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import de.urs.conf.ConfigUtility;
import de.urs.data.DataStats;
import de.urs.data.LitHelper;
import de.urs.data.StaticUtility;

/**
 * Hier wird der Betrieb koordiniert.
 *
 * @author urs
 *
 */
public class Betrieb implements Serializable {
    /**
     * Serial ID
     */
    private static final long serialVersionUID = -6303932969407524759L;
    /**
     * Lokaler Stats Verweis
     */
    private transient DataStats stats;

    /**
     * Konstrukter, bei dem der Zeiger gleich gestzt wird
     *
     * @param stats
     */
    public Betrieb(final DataStats stats) {
        updateZeiger(stats);
    }

    /**
     * Man aktualisiert die Zeiger hiermit
     *
     * @param stats
     */
    public final void updateZeiger(final DataStats stats) {
        this.stats = stats;
    }

    /**
     * Berechnet anhand der Arbeiter, die maximale Anzahl der Verarbeitbaren
     * Produkte.
     *
     * @return maxVerarbeitbar
     */
    public int getMaxVerarbeitbar() {
        int maxver = 0;
        for (final Arbeiter arr : stats.getMitarbeiter()) {
            maxver += arr.getProduzieren();
        }
        return maxver;
    }

    // Arbeiter Funktionen
    /**
     * Berechnet den Lohn aller Arbeiter und zieht ihn ab.
     *
     * @param Meldungsfeld
     */
    public void payDay(final JTextArea meldung) {
        final double kosten = calcPayDay();
        meldung.setText(StaticUtility.getArbBezahl(kosten));
        stats.removeKapital(kosten);
    }

    /**
     * Berechnet den Lohn aller Arbeiter
     *
     * @return Lohn der Arbeiter, außer der Chef
     */
    public double calcPayDay() {
        double kosten = ConfigUtility.WORKERFEE * (stats.getMitarbeiter().size() - 1); // NOPMD by urs on 07.04.20,
                                                                                       // 09:16
        if (kosten < LitHelper.ZERO) {
            kosten = LitHelper.ZERO;
        }
        return kosten;
    }

    /**
     * Entfernt einen Arbeiter
     *
     * @param lblFehler
     */
    public void removeArbeiteranzahl(final JLabel lblFehler) {
        if (stats.getMitarbeiter().size() > LitHelper.ONE) { // NOPMD by urs on 07.04.20, 09:16
            stats.removeArbeiter(lblFehler);
        } else {
            lblFehler.setText(StaticUtility.ONEWORKER);
        }
    }
}

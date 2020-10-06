package de.urs.logic;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.urs.conf.ConfigUtility;
import de.urs.conf.SaveUtility;
import de.urs.data.DataID;
import de.urs.data.DataList;
import de.urs.data.DataStats;
import de.urs.data.LitHelper;
import de.urs.data.StaticUtility;
import de.urs.gson.Setter;
import de.urs.gui.SpielScreen;
import de.urs.objects.IDHelper;
import de.urs.sql.SqlApi;

/**
 * Diese Klasse übernimmt die Kontrolle des Spiels.
 *
 * @author urs
 *
 */
public class Control implements Serializable { // NOPMD by urs on 07.04.20, 08:15
    /**
     * Logger dieser Klasse
     */
    private static final Logger LOG = LogManager.getLogger(Control.class);
    /**
     *
     */
    private static final long serialVersionUID = -6563179222192333570L;
    /**
     * Zeiger
     */
    private transient Betrieb betrieb;
    /**
     * Zeiger
     */
    private transient DataStats stats;

    /**
     * Konstruktor. Nur Zeiger übernahme
     *
     * @param betrieb
     * @param steuer
     * @param DataStats
     */
    public Control(final Betrieb betrieb, final DataStats stats) {
        updateZeiger(betrieb, stats);
    }

    /**
     * Zeiger updaten
     *
     * @param betrieb
     * @param steuer
     * @param DataStats
     */
    public final void updateZeiger(final Betrieb betrieb, final DataStats stats) {
        this.betrieb = betrieb;
        this.stats = stats;
    }

    /**
     * Logik, um ein Sell durchzuführen
     *
     * @param slider
     */
    public void executeSell(final JSlider slider, final JButton btnSlider) {
        final int sliderMenge = slider.getValue();
        if (stats.getProdukte() - sliderMenge < 0) {
            return;
        }
        stats.removeProdukte(sliderMenge);
        final double temp = ConfigUtility.roundbetter(sliderMenge * stats.getVerkaufPreis());
        stats.addKapital(temp);
        stats.addUmsatz(temp);
        slider.setMaximum(stats.getProdukte());
        slider.setMinimum(0);

        stats.addScore(new BigInteger(new StringBuilder().append(slider.getValue()).toString()));
        if (stats.getProdukte() == 0) {
            btnSlider.setEnabled(false);
        }
    }

    /**
     * Logik, um ein Controll.Buy durchzuführen.
     *
     * @param slider
     * @param lblFehler
     * @param btnSlider
     */
    public void executeBuy(final JSlider slider, final JLabel lblFehler, final JButton btnSlider) {
        final int sliderMenge = slider.getValue();
        if (stats.getSchongekauft() <= stats.maximumEinkaufs() && sliderMenge >= 0) {
            final double kosten = ConfigUtility.roundbetter(sliderMenge * stats.getEinkaufPreis());
            stats.addRohstoffe(sliderMenge);
            stats.addSchonGekauft(sliderMenge);
            int max = stats.maximumEinkaufs() - stats.getSchongekauft();
            if (max < 0) {
                max = 0;
            }
            slider.setMaximum(max);
            stats.removeKapital(kosten);
        } else {
            LOG.debug(StaticUtility.SCHONGEKAUFT);
            lblFehler.setText(StaticUtility.SCHONGEKAUFT);
        }
        // Einkaufen Button deaktivieren, weil maximum erreicht
        if (slider.getMaximum() == 0) {
            btnSlider.setEnabled(false);
        }
    }

    /**
     * Logik, um zu bestimmen, ob man zur nächsten Phasen eintretten kann.
     *
     */
    public void executeChangePhase(final SpielScreen spiels, final boolean force) {
        spiels.isInsolvent();
        final JLabel fehler = spiels.getLblFehler();
        final JSlider slider = spiels.getSlider();
        fehler.setText("");
        if (slider.getValue() >= 0) {
            // Tatsächliche Logik im Spiel
            if (stats.getKapital() >= 0 || force) {
                payCalcDay(spiels.getJtaSteuer(), spiels.getJtaSteuer());
                if (stats.isVerkaufPhase()) {
                    einkaufLagerung(spiels);
                } else {
                    verkaufLagerung(spiels);
                } // end of inner if-else
            } else {//
                fehler.setText(StaticUtility.LBLFEHINSOL);
                stats.setVerkaufPhase(true);
            } // end of if-else (Kapital < 0)
        } else {
            // Keine Minuswerte Erlaubt im Slider
            fehler.setText(StaticUtility.LBLFEHLNOMIN);
        }
    }

    /**
     * Initalisierung execute Phasen Change. Phase 1
     *
     * @param spiels
     * @param erzwingen
     */
    private void payCalcDay(final JTextArea steuer, final JTextArea meldung) {
        // Primaere Funktion
        stats.addOnePhasenzaehler();
        // Steuernbescheid
        if (stats.getPhasenzaehler() % ConfigUtility.STEUERPHASE == 0 && stats.getPhasenzaehler() != 0) {
            // Steuern werden kassiert.
            SteuerUtility.calculateTaxes(steuer, stats);
        } // end of if | Steuern
          // Arbeitergehalt
        if (stats.getPhasenzaehler() % ConfigUtility.GEHALTPHASE == 0 && stats.getPhasenzaehler() != 0) {
            // Gehälter werden bezahlt
            betrieb.payDay(meldung);
        } // end of if | Steuern
    }

    /**
     * Initalisierung execute Phasen Change. Phase 2a
     *
     * @param spiels
     * @param erzwingen
     */
    private void einkaufLagerung(final SpielScreen spiels) {
        // Es wird zur Einkaufsphase
        final JButton erteilen = spiels.getBtnAuftragErteilen();
        erteilen.setEnabled(true);
        stats.setVerkaufPhase(false);
        stats.setSchonherrgestellt(0);
        /*
         * Bereitet eine Einkaufsaktion von Rohstoffen vor. GUI Interpretation
         *
         */
        final JSlider sli = spiels.getSlider();
        sli.setValue(0);
        sli.setMinimum(0);
        sli.setMaximum(stats.maximumEinkaufs());

        final JLabel phase = spiels.getLblMengenAnzeiger();
        phase.setText("0");

        final JButton phaWech = spiels.getBtnPhasenWechsel();
        phaWech.setText(StaticUtility.PHAWECHCON1);

        final JButton btnSlider = spiels.getBtnSlider();
        btnSlider.setText(StaticUtility.BTNEINKAUF);
        btnSlider.setEnabled(true);
        stats.genEinkaufspreis();

        final JLabel preis = spiels.getLblPreis();
        preis.setText(StaticUtility.builderPreis(stats.getEinkaufPreis()));
    }

    /**
     * Initalisierung execute Phasen Change. Phase 2b
     *
     * @param spiels
     * @param force
     */
    private void verkaufLagerung(final SpielScreen spiels) {
        // Es wird zur Verkaufsphase
        final JLabel fehlerCon = spiels.getLblFehler();
        if (stats.getProdukte() == 0) {
            fehlerCon.setText(StaticUtility.KEINPRODUKT);
        }
        stats.setSchongekauft(0);
        stats.setVerkaufPhase(true);
        /*
         * Bereitet eine Verkaufsaktion von Produkten vor. GUI Interpretation
         *
         */
        final JButton btnSlider = spiels.getBtnSlider();
        btnSlider.setEnabled(true);

        final JSlider slider = spiels.getSlider();
        slider.setValue(0);
        slider.setMinimum(0);
        slider.setMaximum(stats.getProdukte());

        final JLabel menge = spiels.getLblMengenAnzeiger();
        menge.setText("0");

        final JButton phaWech = spiels.getBtnPhasenWechsel();
        phaWech.setText(StaticUtility.VL2CON2);
        btnSlider.setText(StaticUtility.BTNVERKAUF);
        stats.genVerkaufspreis();

        final JLabel preis = spiels.getLblPreis();
        preis.setText(StaticUtility.getVL2(stats.getVerkaufPreis()));
    }

    /**
     * durchschnittliche Stimmung der Arbeiter bestimmen
     *
     * @param lblStimmung
     */
    public void calcStimmung(final JLabel lblStimmung) {
        int gut = 0;
        int normal = 0;
        int schlecht = 0;

        // Arbeiter durchzählen
        for (final Arbeiter arb : stats.getMitarbeiter()) {
            switch (arb.getArbeiterStimmung()) {
            case GUT:
                gut += 1;
                break;
            case NORMAL:
                normal += 1;
                break;
            case SCHLECHT:
                schlecht += 1;
                break;
            default:
                break;
            }// end of switch
        } // end of for
        _calcStimmung(gut, normal, schlecht, lblStimmung);
    }

    /**
     * Auslagerung der Ergebnisberechnung
     *
     * @param gut
     * @param normal
     * @param schlecht
     * @param lblStimmung
     */
    private void _calcStimmung(final int gut, final int normal, final int schlecht, final JLabel lblStimmung) { // NOPMD
                                                                                                                // by
                                                                                                                // urs
                                                                                                                // on
                                                                                                                // 07.04.20,
                                                                                                                // 09:16

        // Ergebnis berechnen
        int ergebnis = gut * 2 + normal * 1 + schlecht * 0;
        if (!stats.getMitarbeiter().isEmpty()) {
            ergebnis = ergebnis / stats.getMitarbeiter().size();
        }
        if (ergebnis >= 1.5 && ergebnis <= 2) {
            // gut
            lblStimmung.setText("gut");
        } else if (ergebnis < 1.5 && ergebnis >= 0.5) {
            // normal
            lblStimmung.setText("normal");
        } else {
            // schlecht
            lblStimmung.setText("schlecht");
        }
    }

    /**
     * Führt die Herstellung der Produkte aus
     *
     * @param lblFehler
     * @param slider
     */
    public void executeProduzieren(final JLabel lblFehler, final JSlider slider) {
        lblFehler.setText("");
        if (stats.getRohstoffe() == LitHelper.ZERO) {
            JOptionPane.showMessageDialog(null, StaticUtility.NOROHSTOFFE, StaticUtility.INPUT,
                    JOptionPane.CLOSED_OPTION);
        } else {
            // Maximale Produkte zur Umwandlung berechnen
            final int maxVerarbeitbar = betrieb.getMaxVerarbeitbar() - stats.getSchonherrgestellt();
            int avaibel;
            double produzierKosten;
            if (stats.getRohstoffe() <= maxVerarbeitbar) {
                LOG.debug("<= maxVerarbeitbar");
                avaibel = stats.getRohstoffe();
            } else {
                LOG.debug("> maxVerarbeitbar");
                avaibel = maxVerarbeitbar;
            }

            // User Eingabe
            final String userwahl = JOptionPane.showInputDialog(StaticUtility.getWILLPRODU(stats.getProduzierKosten()),
                    avaibel);
            int useranzahl;
            try {
                if (userwahl == null) {
                    // Fals der Nutzer kein Name eingibt, sofort abrechen
                    return;
                }
                // Nur positive Zahlen erlaubt
                useranzahl = Math.abs(Integer.parseInt(userwahl));
                produzierKosten = useranzahl * stats.getProduzierKosten(); // NOPMD by urs on 07.04.20, 08:15
                if (useranzahl <= stats.getRohstoffe()) {
                    // Ja-Nein Option
                    final int eingabe = JOptionPane.showConfirmDialog(null,
                            StaticUtility.getWIRKLICHPROD(useranzahl, produzierKosten), StaticUtility.INPUT,
                            JOptionPane.YES_NO_OPTION);
                    // User klickte auf Ja (0)
                    if (eingabe == LitHelper.ZERO) {
                        if (useranzahl <= maxVerarbeitbar) {
                            LOG.info(useranzahl + StaticUtility.LOGPRO); // NOPMD by urs on 09.04.20, 08:25
                            stats.addScore(new BigInteger(new StringBuffer().append(useranzahl).toString()));
                            stats.addSchonherrgestellt(useranzahl);
                            stats.addProducts(useranzahl * ConfigUtility.RZPRATE);
                            stats.removeRohstoffe(useranzahl);
                            stats.removeKapital(produzierKosten);
                            if (stats.isVerkaufPhase()) {
                                slider.setMaximum(stats.getProdukte());
                            }
                        } else {// end of if (useranzahl <= maxVerarbeitbar - stats.getSchonherrgestellt())
                            lblFehler.setText(StaticUtility.NOENOGHWORK);
                        }
                    } // end of if ((eingabe == LitHelper.ZERO) )
                } else {
                    lblFehler.setText(StaticUtility.KEINEROHST);
                }
            } catch (final NumberFormatException e) {
                lblFehler.setText(StaticUtility.NOZAHLINPUT);
                LOG.error("NumberInputException: ", e);
            }
        }

    }

    /**
     * Methode um den Spielstand zu sichern
     */
    public void executeSave(final DataStats stats, final DataList list, final DataID idHelper) {
        // SQL initalisieren
        final SqlApi sapi = new SqlApi();

        // Setter initalisieren
        final Setter set = new Setter();
        final String statsJSON = set.convertData(stats);
        final String dlJSON = set.convertList(list);
        final String idJSON = set.convertID(idHelper);

        // Ausführen
        try {
            sapi.querySaveSpieldaten(statsJSON, dlJSON, idHelper.getID());
            IDHelper.write(SaveUtility.getIDName(), idJSON, false);
            IDHelper.write(SaveUtility.getIDLogName(), idJSON, true);
        } catch (final SQLException e1) {
            LOG.error("SQLException @ executeSave", e1);
        } catch (final IOException e2) {
            LOG.fatal("IOException @ executeSave", e2);
        }
    }

    /**
     * Führt die Insolvenz aus und beendet das Spiel.
     *
     * @param list
     * @param idhelper
     */
    public void executeInsolvenz(final DataList list, final DataID idhelper) {
        // Spiel speichern, bevor das Programm beendet wird
        executeSave(stats, list, idhelper);
        // Benachrichtigung, über das Verloren sein
        JOptionPane.showMessageDialog(null, StaticUtility.VERLOREN, StaticUtility.INPUT, JOptionPane.CLOSED_OPTION);
        // Score anzeigen
        JOptionPane.showMessageDialog(null, StaticUtility.getSCOREIST(stats.getScore()), StaticUtility.INPUT,
                JOptionPane.CLOSED_OPTION);
        // Einverständnis zur Veröffentlichtungen
        final int userwahl = JOptionPane.showConfirmDialog(null, StaticUtility.SCOREVEROEFF, StaticUtility.EINVERSCORE,
                JOptionPane.YES_NO_OPTION);
        if (userwahl == LitHelper.ZERO) {
            final String name = JOptionPane.showInputDialog(StaticUtility.INPUTNAME);
            try {
                new SqlApi().querySaveScore(name, stats.getScore(), idhelper.getID());
                JOptionPane.showMessageDialog(null, StaticUtility.SCOREVEROEFF2, StaticUtility.INPUT,
                        JOptionPane.CLOSED_OPTION);

            } catch (final SQLException e) {
                LOG.error("SQLException @ insolvent", e);
            }
        }
        // Spieldatei löschen, um Weiterspielen zu verhindern.
        final File file = new File(SaveUtility.getIDName());
        if (file.exists()) {
            file.delete();
        }
        new SqlApi().deleteSpielstand(idhelper.getID());
        System.exit(0); // NOPMD by urs on 07.04.20, 08:13
    }
}// end of class

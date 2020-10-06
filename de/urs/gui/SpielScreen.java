package de.urs.gui; // NOPMD by urs on 07.04.20, 08:10

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.urs.conf.ConfigUtility;
import de.urs.data.DataID;
import de.urs.data.DataList;
import de.urs.data.DataStats;
import de.urs.data.LitHelper;
import de.urs.data.StaticUtility;
import de.urs.gson.Getter;
import de.urs.logic.Arbeiter;
import de.urs.logic.Betrieb;
import de.urs.logic.Control;
import de.urs.logic.EventManager;
import de.urs.objects.ImagePanel;
import de.urs.objects.NoEntryException;

/**
 * Den Spielscreen initalisieren
 *
 * @author urs
 *
 */
public class SpielScreen extends JFrame
        implements ActionListener, Serializable, MouseListener, ChangeListener, WindowListener { // NOPMD
    // by
    // urs
    // on
    // 07.04.20,
    // 08:10

    /**
     * Logger dieser Klasse
     */
    private static final Logger LOG = LogManager.getLogger(SpielScreen.class);
    /**
     * Serial ID
     */
    private static final long serialVersionUID = 3281969866472549202L;

    // Labels
    /**
     * Label
     */
    private transient JLabel lblArbAnzahl;
    /**
     * Label
     */
    private transient JLabel lblVermogen;
    /**
     * Label
     */
    private transient JLabel lblUmsi;
    /**
     * Label
     */
    private transient JLabel lblRohstoffe;
    /**
     * Label
     */
    private transient JLabel lblProdukte;
    /**
     * Label
     */
    private transient JLabel lblmaxAnzeige;
    /**
     * Label
     */
    private transient JLabel lblFehler;
    /**
     * Label
     */
    private transient JLabel lblStimAnzr;
    /**
     * Label
     */
    private transient JLabel lblMengAnzr;

    /**
     * Label
     */
    private transient JLabel lblPreis;
    /**
     * Label
     */
    private transient JLabel lblGesamtpreis;
    /**
     * Label
     */
    private transient JLabel lblPhaseZeiger;
    /**
     * Label
     */
    private transient JLabel lblInventar;
    /**
     * Label
     */
    private transient JLabel lblArbeiterInfo;
    /**
     * Label
     */
    private transient JLabel lblPhase;
    /**
     * Label
     */
    private transient JLabel lblMaxVerar;
    /**
     * Label
     */
    private transient JLabel lblRadio;
    /**
     * Label
     */
    private transient JLabel lblSteuerinfo;
    /**
     * Label
     */
    private transient JLabel lblMenge;
    /**
     * Label
     */
    private transient JLabel lblUmsatz;
    /**
     * Label
     */
    private transient JLabel lblKapital;
    /**
     * Label
     */
    private transient JLabel lblStimmun;
    /**
     * Label
     */
    private transient JLabel lblArbeiter;

    /**
     * Slider
     */
    private transient JSlider slider;

    // TextArea
    /**
     * JTextArea
     */
    private transient JTextArea jtaSteuer;
    /**
     * JTextArea
     */
    private transient JTextArea jtaMeldung;
    // Buttons
    /**
     * Buttons
     */
    private transient JButton btnSlider;
    /**
     * Buttons
     */
    private transient JButton btnArbop;
    /**
     * Buttons
     */
    private transient JButton btnPhWecl;
    /**
     * Buttons
     */
    private transient JButton btnAufErt;
    /**
     * Buttons
     */
    private transient JButton btnInsol;

    // ScrollPane
    /**
     * ScrollPane 1
     */
    private transient JScrollPane scrollPaneSteuer;

    /**
     * ScrollPane 2
     */
    private transient JScrollPane scrollPaneMeldung;

    // Logik

    /*
     * Logik Objekt
     */
    private transient Betrieb betrieb;

    /*
     * Logik Objekt
     */
    private transient Control controll;

    /*
     * Speicher Objekt
     */
    private transient DataStats stats;

    /*
     * Speicher Objekt
     */
    private transient DataList list;

    /*
     * Speicher Objekt
     */
    private transient DataID idhelper;

    /*
     * Logik Objekt
     */
    private transient EventManager eventmanager;
    // nicht final, weil sonst Eclipse meckert, dass es nicht ausführen kann

    /**
     * Inhaltsplatte
     */
    private final JPanel contentPane;

    // Ende
    // ------------------------------------------------------//
    /**
     * Dialog String
     */
    private static final String DIALOG = "Dialog";
    // ------------------------------------------------------//

    /**
     * Erschaft den Spielscreen
     *
     * @wbp.parser.constructor
     */
    public SpielScreen(final boolean fastboot) { // NOPMD by urs on 08.04.20, 07:21
        super();

        // Frame Settings
        setTitle(StaticUtility.NAME);

        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 570);
        contentPane = new ImagePanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        addMouseListener(this);
        addWindowListener(this);

        // Betrieb
        if (fastboot) {
            // Spiel starten
            LOG.debug(StaticUtility.BTNSTART);
            stats = new DataStats();
            idhelper = new DataID();
            idhelper.generateID();
            list = new DataList();
            stats.getMitarbeiter().add(new Arbeiter(0));
        } else {
            // Spiel laden
            LOG.debug(StaticUtility.BTNLADEN);
            try {
                final Getter getter = new Getter();
                stats = getter.getStats();
                list = getter.getList();
                idhelper = getter.getIDhelper();
                stats.fixZaehlerByLoad();
            } catch (final SQLException | NoEntryException e) {
                dispose();
                JOptionPane.showMessageDialog(this, StaticUtility.UNKNOWNID);
                LOG.error(StaticUtility.UNKNOWNID, e);
                new Hauptmenu();
                return;
            } catch (final IOException e) {
                dispose();
                JOptionPane.showMessageDialog(this, StaticUtility.ERRORLOAD);
                LOG.fatal(StaticUtility.ERRORLOAD, e);
                new Hauptmenu();
                return;
            }
        } // end of if-else

        // Initalisierungen
        LOG.debug("Initalisieren");
        initSlider();

        initLabel1();
        initLabel2();
        initLabelText();

        initButton();
        initTextArea();
        initScrollPane();

        // Content adden
        LOG.debug("Adden");
        contentPane.setLayout(null);

        contentPane.add(lblFehler);
        contentPane.add(btnInsol);
        contentPane.add(slider);
        contentPane.add(lblMenge);
        contentPane.add(lblMengAnzr);
        contentPane.add(lblPreis);
        contentPane.add(lblGesamtpreis);
        contentPane.add(lblRohstoffe);
        contentPane.add(lblArbeiter);
        contentPane.add(lblArbAnzahl);
        contentPane.add(lblStimmun);
        contentPane.add(lblStimAnzr);
        contentPane.add(lblKapital);
        contentPane.add(lblVermogen);
        contentPane.add(lblUmsatz);
        contentPane.add(lblUmsi);
        contentPane.add(btnArbop);
        contentPane.add(btnAufErt);
        contentPane.add(btnSlider);
        contentPane.add(lblProdukte);
        contentPane.add(lblmaxAnzeige);
        contentPane.add(lblMaxVerar);
        contentPane.add(lblInventar);
        contentPane.add(lblRadio);
        contentPane.add(btnPhWecl);
        contentPane.add(lblSteuerinfo);
        contentPane.add(scrollPaneMeldung);
        contentPane.add(scrollPaneSteuer);
        contentPane.add(lblPhase);
        contentPane.add(lblPhaseZeiger);
        contentPane.add(lblArbeiterInfo);
        setVisible(true);

        // Abhängige Variabeln
        betrieb = new Betrieb(stats);
        controll = new Control(betrieb, stats);
        // Eventmanager starten
        eventmanager = new EventManager(stats, list, controll, slider, jtaMeldung);

        LOG.debug("Spiel starten");
        updateLabels();
        // Spiel starten
        changePhase();
    }

    private void initSlider() {
        slider = new JSlider();
        slider.setBounds(47, 312, 711, 27);
        slider.setValue(0);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);
        slider.setOpaque(false);
    }

    private void initTextArea() {
        jtaMeldung = new JTextArea();
        jtaMeldung.setForeground(Color.BLUE);
        jtaMeldung.setEditable(false);
        jtaMeldung.setBorder(null);
        jtaMeldung.setColumns(10);
        jtaMeldung.setBackground(null);
        jtaMeldung.setOpaque(false);

        jtaSteuer = new JTextArea(StaticUtility.JTASTEUER01);
        jtaSteuer.setForeground(Color.BLUE);
        jtaSteuer.setEditable(false);
        jtaSteuer.setColumns(10);
        jtaSteuer.setBorder(null);
        jtaSteuer.setBackground(null);
        jtaSteuer.setOpaque(false);
    }

    private void initScrollPane() {
        scrollPaneSteuer = new JScrollPane(jtaSteuer);
        scrollPaneSteuer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneSteuer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneSteuer.setBounds(406, 108, 357, 173);
        scrollPaneSteuer.setBorder(null);
        scrollPaneSteuer.getViewport().setOpaque(false);
        scrollPaneSteuer.setOpaque(false);

        scrollPaneMeldung = new JScrollPane(jtaMeldung);
        scrollPaneMeldung.setBounds(47, 108, 347, 135);
        scrollPaneMeldung.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneMeldung.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneMeldung.setBorder(null);
        scrollPaneMeldung.getViewport().setOpaque(false);
        scrollPaneMeldung.setOpaque(false);
    }

    private void initButton() {
        // Buttons Options
        btnInsol = new JButton(StaticUtility.INSOLVENT);
        btnInsol.setBounds(47, 273, 711, 27);
        btnInsol.addActionListener(this);
        btnInsol.setVisible(false);

        btnArbop = new JButton(StaticUtility.LBLARBEITEROPTIO);
        btnArbop.setBounds(47, 401, 151, 25);
        btnArbop.addActionListener(this);

        btnAufErt = new JButton(StaticUtility.BTNAUFTRAG);
        btnAufErt.setBounds(216, 401, 143, 25);
        btnAufErt.addActionListener(this);

        // Ohne StaticHelper bezug
        btnSlider = new JButton("an");
        btnSlider.setBounds(386, 401, 130, 25);
        btnSlider.addActionListener(this);

        btnPhWecl = new JButton("an");
        btnPhWecl.setBounds(52, 41, 711, 25);
        btnPhWecl.addActionListener(this);

    }

    private void initLabel1() {
        lblArbeiter = new JLabel();
        lblArbeiter.setBounds(48, 443, 56, 15);

        lblStimmun = new JLabel();
        lblStimmun.setBounds(124, 443, 87, 15);

        lblKapital = new JLabel();
        lblKapital.setBounds(229, 443, 70, 15);

        lblUmsatz = new JLabel();
        lblUmsatz.setBounds(317, 443, 51, 15);

        lblMenge = new JLabel();
        lblMenge.setBounds(47, 366, 55, 15);

        lblSteuerinfo = new JLabel();
        lblSteuerinfo.setBounds(587, 78, 176, 18);
        lblSteuerinfo.setFont(new Font(DIALOG, Font.BOLD, 15));
        lblSteuerinfo.setForeground(Color.RED);

        lblRadio = new JLabel();
        lblRadio.setBounds(52, 78, 95, 18);
        lblRadio.setFont(new Font(DIALOG, Font.BOLD, 15));
        lblRadio.setForeground(Color.GREEN);

        lblRohstoffe = new JLabel();
        lblRohstoffe.setForeground(Color.BLACK);
        lblRohstoffe.setBounds(597, 408, 65, 18);
        lblRohstoffe.setFont(new Font(DIALOG, Font.BOLD, 20));

        lblProdukte = new JLabel();
        lblProdukte.setForeground(Color.BLACK);
        lblProdukte.setBounds(674, 408, 65, 18);
        lblProdukte.setFont(new Font(DIALOG, Font.BOLD, 20));

    }

    private void initLabel2() {
        lblPreis = new JLabel();
        lblPreis.setBounds(262, 366, 83, 15);

        lblMaxVerar = new JLabel();
        lblMaxVerar.setBounds(396, 443, 176, 15);

        lblGesamtpreis = new JLabel();
        lblGesamtpreis.setBounds(365, 366, 151, 15);

        lblPhase = new JLabel();
        lblPhase.setBounds(579, 442, 60, 17);

        lblArbeiterInfo = new JLabel("");
        lblArbeiterInfo.setBounds(47, 246, 711, 15);
        lblArbeiterInfo.setFont(new Font(DIALOG, Font.PLAIN, 12));

        lblFehler = new JLabel("");
        lblFehler.setForeground(Color.RED);
        lblFehler.setBounds(42, 339, 716, 15);

        lblInventar = new JLabel();
        lblInventar.setBounds(630, 339, 58, 15);

        // Ohne StaticHelper bezug
        lblVermogen = new JLabel("an");
        lblVermogen.setBounds(229, 475, 83, 15);
        lblVermogen.setFont(new Font(DIALOG, Font.PLAIN, 12));

        lblUmsi = new JLabel("an");
        lblUmsi.setBounds(317, 475, 77, 15);
        lblUmsi.setFont(new Font(DIALOG, Font.PLAIN, 12));

        lblMengAnzr = new JLabel("an");
        lblMengAnzr.setBounds(120, 364, 124, 19);

        lblPhaseZeiger = new JLabel("0");
        lblPhaseZeiger.setFont(new Font(DIALOG, Font.PLAIN, 12));
        lblPhaseZeiger.setBounds(579, 474, 60, 17);

        lblmaxAnzeige = new JLabel("an");
        lblmaxAnzeige.setBounds(406, 475, 63, 15);
        lblmaxAnzeige.setFont(new Font(DIALOG, Font.PLAIN, 12));

        lblArbAnzahl = new JLabel("an");
        lblArbAnzahl.setBounds(47, 475, 65, 15);
        lblArbAnzahl.setFont(new Font(DIALOG, Font.PLAIN, 12));

        lblStimAnzr = new JLabel("an");
        lblStimAnzr.setBounds(124, 475, 87, 15);
        lblStimAnzr.setFont(new Font(DIALOG, Font.PLAIN, 12));
    }

    /**
     * Spielstand speichern und dann Programm schließen
     */
    private void saveGameOnExit() {
        LOG.debug("saveGameOnExit");
        controll.executeSave(stats, list, idhelper);
        // Programm schließen
        System.exit(0); // NOPMD by urs on 08.04.20, 07:21
    }

    /**
     * "Auftrag erteilen" Button. Pro Arbeiter kann eine bestimmte Anzahl von
     * Rohstoffen verarbeitet werden.
     */
    private void produzieren() {
        LOG.debug("produzieren");
        controll.executeProduzieren(lblFehler, slider);
        updateLabels();

    }// end of method produce

    /**
     * Hiermit wechselt man die Phasen (Einkauf- und Verkauf) im Spiel. Alle 10
     * Runden wird hiermit die Steuern vom Konto abgebucht.
     */
    private void changePhase() {
        LOG.debug("changePhase");
        stats.addScore(BigInteger.ONE);
        // Stimmung berechnen
        controll.calcStimmung(lblStimAnzr);
        // Phasen wechsel durchführen
        if (stats.isVerkaufPhase()) {
            controll.executeChangePhase(this, false);
        } else {
            controll.executeChangePhase(this, true);
        }

        // Das immer zuschluss ausführen
        eventmanager.checkEvent(stats.getPhasenzaehler());
        updateLabels();
    }// end of change

    /**
     * Arbeiteroptionen öffnen. Ort: im Spielscreen
     */
    private void showWorkoptionsAction() {
        LOG.debug("showWorkoptionsAction");
        new ArbeiterGui(betrieb, stats);

    }

    /**
     * Button zum Einkaufen und Verkaufen
     */
    private void queryButtonEinVer() {
        LOG.debug("queryButtonEinVer");
        lblFehler.setText("");
        if (stats.isVerkaufPhase()) {
            // Verkaufsphase
            controll.executeSell(slider, btnSlider);
        } else {
            // Einkaufsphase
            controll.executeBuy(slider, lblFehler, btnSlider);
        }
        updateLabels();
    }

    /**
     * Funktion zum Verändern des JTextFields, wenn sich der Slider sich bewegt.
     */
    private void changeSliderEvent() {
        LOG.debug("changeSliderEvent");
        lblMengAnzr.setText(new StringBuilder().append(slider.getValue()).toString());
        calcGesamtpreis();
    }

    /**
     * Spiel beenden und Score speichern. Sowie Spielstand löschen
     */
    private void insolvent() {
        controll.executeInsolvenz(list, idhelper);
    }

    /**
     * Gesamtpreis Kalkulieren
     */
    private void calcGesamtpreis() {
        LOG.debug("calcGesamtpreis");
        if (stats.isVerkaufPhase()) {
            // Verkaufsphase
            lblGesamtpreis.setText(new StringBuilder().append(StaticUtility.LBLGESAMTPREIS)
                    .append(ConfigUtility.roundbetter(slider.getValue() * stats.getVerkaufPreis())).toString());
        } else {
            // Einkaufsphase
            lblGesamtpreis.setText(new StringBuilder().append(StaticUtility.LBLGESAMTPREIS)
                    .append(ConfigUtility.roundbetter(slider.getValue() * stats.getEinkaufPreis())).toString());
        }
    }

    /**
     * untere Komponentenupdates für besser Spielperformens
     */
    private void updateLabels() {
        lblArbAnzahl.setText(new StringBuilder().append(stats.getMitarbeiter().size()).toString()); // NOPMD by urs on
                                                                                                    // 08.04.20, 07:21
        lblVermogen.setText(new StringBuilder().append(stats.getKapital()).append(StaticUtility.WAEHRUNG).toString());
        lblUmsi.setText(new StringBuilder().append(stats.getUmsatz()).append(StaticUtility.WAEHRUNG).toString());
        lblRohstoffe.setText(new StringBuilder().append(stats.getRohstoffe()).toString());
        lblProdukte.setText(new StringBuilder().append(stats.getProdukte()).toString());
        lblmaxAnzeige.setText(new StringBuilder().append(betrieb.getMaxVerarbeitbar()).toString());
        lblPhaseZeiger.setText(new StringBuilder().append(stats.getPhasenzaehler()).toString());

        // Auftragserteilen Button
        final int maxVerarbeitbar = betrieb.getMaxVerarbeitbar();
        if (stats.getSchonherrgestellt() >= maxVerarbeitbar) {
            btnAufErt.setEnabled(false);
        } else {
            btnAufErt.setEnabled(true);
        }
    }

    private void initLabelText() {
        lblArbeiter.setText(StaticUtility.LBLARBEITER);
        lblStimmun.setText(StaticUtility.LBLSTIMMUNG);

        lblKapital.setText(StaticUtility.LBLKAPITAL);
        lblUmsatz.setText(StaticUtility.LBLUMSATZ);
        lblMenge.setText(StaticUtility.LBLMENGE);
        lblSteuerinfo.setText(StaticUtility.LBLSTEUERINFO);
        lblRadio.setText(StaticUtility.LBLRADIO);
//        lblRohstoffe.setText(StaticUtility.ROHSTOFFE);
//        lblProdukte.setText(StaticUtility.PRODUKTE);
        lblPreis.setText(StaticUtility.LBLPREIS);
        lblMaxVerar.setText(StaticUtility.LBLMAXROHSTOFFE);
        lblGesamtpreis.setText(StaticUtility.LBLGESAMTPREIS);
        lblPhase.setText(StaticUtility.PHASE);
        lblInventar.setText(StaticUtility.LBLINVENTAR);
    }

    /**
     * Insolvent gehen Logic
     */
    public void isInsolvent() {
        // Insolvent gehen
        if (stats.getKapital() < LitHelper.ZERO) {
            btnInsol.setVisible(true);
            lblFehler.setText(StaticUtility.LBLFEHINSOL);
        } else {
            btnInsol.setVisible(false);
        }
    }

    // TODO: Listener
    /**
     * Action Event Manager
     */
    @Override
    public void actionPerformed(final ActionEvent event) {
        LOG.debug("Button Klick: " + event.getSource().toString()); // NOPMD by urs on 06.04.20, 13:54
        if (event.getSource() == btnSlider) {
            queryButtonEinVer();
        } else if (event.getSource() == btnPhWecl) {
            changePhase();
        } else if (event.getSource() == btnAufErt) {
            produzieren();
        } else if (event.getSource() == btnInsol) {
            insolvent();
        } else if (event.getSource() == btnArbop) {// 5
            showWorkoptionsAction();
        }
        updateLabels();
    }

    /**
     * Mouse Event Manager
     */
    @Override
    public void mouseClicked(final MouseEvent event) {
        updateLabels();
    }

    /**
     * Mouse Event Manager
     */
    @Override
    public void mouseEntered(final MouseEvent event) {
        updateLabels();
    }

    /**
     * Mouse Event Manager
     */
    @Override
    public void mouseExited(final MouseEvent event) {
        updateLabels();
    }

    /**
     * Mouse Event Manager
     */
    @Override
    public void mousePressed(final MouseEvent event) {
        updateLabels();
    }

    /**
     * Mouse Event Manager
     */
    @Override
    public void mouseReleased(final MouseEvent event) {
        updateLabels();
    }

    /**
     * Change Event Manager
     */
    @Override
    public void stateChanged(final ChangeEvent event) {
        if (event.getSource() == slider) {
            changeSliderEvent();
        }
    }

    /**
     * Window Event Manager
     */
    @Override
    public void windowOpened(final WindowEvent e) {

    }

    /**
     * Window Event Manager
     */
    @Override
    public void windowClosed(final WindowEvent e) {

    }

    /**
     * Window Event Manager
     */
    @Override
    public void windowClosing(final WindowEvent e) {
        LOG.info("Spiel wurde beendet");
        saveGameOnExit();

    }

    /**
     * Window Event Manager
     */
    @Override
    public void windowIconified(final WindowEvent e) {

    }

    /**
     * Window Event Manager
     */
    @Override
    public void windowDeiconified(final WindowEvent e) {

    }

    /**
     * Window Event Manager
     */
    @Override
    public void windowActivated(final WindowEvent e) {

    }

    /**
     * Window Event Manager
     */
    @Override
    public void windowDeactivated(final WindowEvent e) {

    }

    // Getter

    /**
     *
     * @return Arbeiteranzahl anzeiger
     */
    public JLabel getLblArbeiterAnzahl() {
        return lblArbAnzahl;
    }

    /**
     *
     * @return Kapital Anzeiger
     */
    public JLabel getLblVermogen() {
        return lblVermogen;
    }

    /**
     *
     * @return Umsatzanzeiger
     */
    public JLabel getLblUmsi() {
        return lblUmsi;
    }

    /**
     *
     * @return Rohstoff Anzeiger
     */
    public JLabel getLblRohstoffe() {
        return lblRohstoffe;
    }

    /**
     *
     * @return Produktanzahl Anzeiger
     */
    public JLabel getLblProdukte() {
        return lblProdukte;
    }

    /**
     *
     * @return Label maxAnzeige
     */
    public JLabel getLblmaxAnzeige() {
        return lblmaxAnzeige;
    }

    /**
     *
     * @return Fehler Text
     */
    public JLabel getLblFehler() {
        return lblFehler;
    }

    /**
     *
     * @return StimmungAnzeiger
     */
    public JLabel getLblStimmungAnzeiger() {
        return lblStimAnzr;
    }

    /**
     *
     * @return lblMengAnzr
     */
    public JLabel getLblMengenAnzeiger() {
        return lblMengAnzr;
    }

    /**
     *
     * @return lblPreis
     */
    public JLabel getLblPreis() {
        return lblPreis;
    }

    /**
     *
     * @return lblGesamtpreis
     */
    public JLabel getLblGesamtpreis() {
        return lblGesamtpreis;
    }

    /**
     *
     * @return slider
     */
    public JSlider getSlider() {
        return slider;
    }

    /**
     *
     * @return SteuerInfo
     */
    public JTextArea getJtaSteuer() {
        return jtaSteuer;
    }

    /**
     *
     * @return Meldungen
     */
    public JTextArea getJtaMeldung() {
        return jtaMeldung;
    }

    /**
     *
     * @return Slider Button
     */
    public JButton getBtnSlider() {
        return btnSlider;
    }

    /**
     *
     * @return getBtnArbeiteroptionen
     */
    public JButton getBtnArbeiteroptionen() {
        return btnArbop;
    }

    /**
     *
     * @return PhasenWechsel Button
     */
    public JButton getBtnPhasenWechsel() {
        return btnPhWecl;
    }

    /**
     *
     * @return Auftrag Erteilen Button
     */
    public JButton getBtnAuftragErteilen() {
        return btnAufErt;
    }
}

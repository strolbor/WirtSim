package de.urs.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.urs.conf.ConfigUtility;
import de.urs.data.DataStats;
import de.urs.data.StaticUtility;
import de.urs.logic.Arbeiter;
import de.urs.logic.Betrieb;

/**
 * Arbeiter GUI
 *
 * @author urs
 *
 */
public class ArbeiterGui extends JFrame implements ActionListener, WindowListener { // NOPMD by urs on 09.04.20, 08:23
    /**
     * Logger für die Klasse ArbeiterGUI
     */
    private static final Logger LOG = LogManager.getLogger(ArbeiterGui.class);
    /**
     * Serial ID
     */
    private static final long serialVersionUID = -8311417678830699324L;
    /**
     * Button
     */
    private final JButton btnSchliesen; // NOPMD by urs on 07.04.20, 08:22
    /**
     * Button
     */
    private final JButton btnEntlassen; // NOPMD by urs on 07.04.20, 08:22
    /**
     * Button
     */
    private final JButton btnAnheuern; // NOPMD by urs on 07.04.20, 08:22
    /**
     * Label
     */
    private final JLabel lblAnzahl; // NOPMD by urs on 07.04.20, 08:22
    /**
     * Label
     */
    private final JLabel lblLaufendeKosten; // NOPMD by urs on 07.04.20, 08:22
    /**
     * Label
     */
    private final JLabel lblFehlerArbeiter; // NOPMD by urs on 07.04.20, 08:22
    /**
     * Objekt
     */
    private final DataStats stats; // NOPMD by urs on 07.04.20, 08:22
    /**
     * Objekt
     */
    private final Betrieb betrieb; // NOPMD by urs on 07.04.20, 08:22
    /**
     * Text-Objekt
     */
    private final JTextArea txtrHallo; // NOPMD by urs on 07.04.20, 08:22

    /**
     * DIALOG
     */
    private static final String DIALOG = "Dialog";

    /**
     * Create the frame.
     */
    public ArbeiterGui(final Betrieb betrieb, final DataStats stats) {
        super();
        this.stats = stats;
        this.betrieb = betrieb;
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 527, 406);
        final JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setResizable(false);
        addWindowListener(this);
        setVisible(true);
        setTitle(StaticUtility.NAME + " " + StaticUtility.LBLARBEITEROPTIO);

        // Labels
        final JLabel lblArbOp = new JLabel(StaticUtility.LBLARBEITEROPTIO);
        lblArbOp.setBounds(201, 0, 117, 15);

        lblAnzahl = new JLabel(StaticUtility.LBLANZAHL);
        lblAnzahl.setBounds(17, 138, 103, 15);
        lblAnzahl.setFont(new Font(DIALOG, Font.PLAIN, 12));

        lblFehlerArbeiter = new JLabel();
        lblFehlerArbeiter.setBounds(79, 212, 0, 0);
        lblFehlerArbeiter.setForeground(Color.RED);

        final JLabel lblInformationen = new JLabel(StaticUtility.LBLINFO);
        lblInformationen.setBounds(12, 165, 258, 15);

        final JLabel lblArbeKostenGeld = new JLabel(StaticUtility.ARBGELD);
        lblArbeKostenGeld.setBounds(12, 192, 475, 15);
        lblArbeKostenGeld.setFont(new Font(DIALOG, Font.PLAIN, 12));

        lblLaufendeKosten = new JLabel(StaticUtility.LAUFKOST);
        lblLaufendeKosten.setBounds(124, 138, 363, 15);
        lblLaufendeKosten.setFont(new Font(DIALOG, Font.PLAIN, 12));

        // JButtons
        btnAnheuern = new JButton(StaticUtility.BTNANWERBEN);
        btnAnheuern.setBounds(17, 27, 485, 25);
        btnAnheuern.addActionListener(this);

        btnEntlassen = new JButton(StaticUtility.BTNFEUERN);
        btnEntlassen.setBounds(17, 64, 485, 25);
        btnEntlassen.addActionListener(this);

        btnSchliesen = new JButton(StaticUtility.BTNCLOSE);
        btnSchliesen.setBounds(17, 101, 485, 25);
        btnSchliesen.addActionListener(this);

//        JTextArea
        txtrHallo = new JTextArea();
        txtrHallo.setBounds(17, 212, 50, 50);

//        JScrollPane
        final JScrollPane jsp = new JScrollPane(txtrHallo);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBounds(17, 212, 470, 150);

        // Inhalt an Pane adden
        contentPane.setLayout(null);
        contentPane.add(btnAnheuern);
        contentPane.add(btnEntlassen);
        contentPane.add(btnSchliesen);
        contentPane.add(lblFehlerArbeiter);
        contentPane.add(lblArbOp);
        contentPane.add(lblAnzahl);
        contentPane.add(lblLaufendeKosten);
        contentPane.add(lblInformationen);
        contentPane.add(lblArbeKostenGeld);
        contentPane.add(jsp);

        updateAnzahlLabel();
    }

    /**
     * Hiermit Feuert man ein Arbeiter
     */
    private void buttonFeuern() {
        LOG.debug("buttonFeuern");
        betrieb.removeArbeiteranzahl(lblFehlerArbeiter);
        updateAnzahlLabel();
    }

    /**
     * Hiermit heuert man ein Arbeiter an
     */
    private void buttonAnheuern() {
        LOG.debug("buttonAnheuern");
        if (stats.getKapital() >= ConfigUtility.WORKERFEE) {
            stats.addArbeiter();
            stats.removeKapital(ConfigUtility.WORKERFEE);
        } else {
            lblFehlerArbeiter.setText(StaticUtility.NOGELDARB);
        }
        updateAnzahlLabel();
    }

    /**
     * Aktualisiert die Labels im GUI
     */
    public final void updateAnzahlLabel() {
        LOG.debug("updateAnzahlLabel");
        lblAnzahl.setText(
                new StringBuilder().append(StaticUtility.LBLANZAHL).append(stats.getMitarbeiter().size()).toString()); // NOPMD
                                                                                                                       // by
                                                                                                                       // urs
                                                                                                                       // on
                                                                                                                       // 07.04.20,
                                                                                                                       // 08:17
        lblLaufendeKosten.setText(
                new StringBuilder().append(StaticUtility.LBLGEHALTINFO).append(betrieb.calcPayDay()).toString());
        createList();
    }

    /**
     * Erstellt eine Liste der Arbeiter und ihre Daten
     */
    private void createList() {
        LOG.debug("createList");
        final StringBuilder append = new StringBuilder(4048).append("Name - Position - Stimmung - Verarbeitbar\n");
        for (final Arbeiter arb : stats.getMitarbeiter()) { // NOPMD by urs on 07.04.20, 08:07
            append.append(arb.getAnrede()).append(" ").append(arb.getName()).append(" - ").append(arb.getPosition()) // NOPMD
                                                                                                                     // by
                                                                                                                     // urs
                                                                                                                     // on
                                                                                                                     // 07.04.20,
                                                                                                                     // 08:22
                    .append(" - ").append(arb.getArbeiterStimmungString()).append(" - ").append(arb.getProduzieren())
                    .append("\n"); // NOPMD by urs on 07.04.20, 08:21
        }
        txtrHallo.setText(append.toString());
    }

    /**
     * Action Listener Verwaltung
     *
     */
    @Override
    public void actionPerformed(final ActionEvent event) {
        if (event.getSource() == btnAnheuern) {
            buttonAnheuern();
        } else if (event.getSource() == btnEntlassen) {
            buttonFeuern();
        } else if (event.getSource() == btnSchliesen) {
            dispose();
        }
    }

    /**
     * Window Event
     */
    @Override
    public void windowOpened(final WindowEvent event) {

    }

    /**
     * Window Event
     */
    @Override
    public void windowClosing(final WindowEvent event) {
        dispose();

    }

    /**
     * Window Event
     */
    @Override
    public void windowClosed(final WindowEvent event) {

    }

    /**
     * Window Event
     */
    @Override
    public void windowIconified(final WindowEvent event) {

    }

    /**
     * Window Event
     */
    @Override
    public void windowDeiconified(final WindowEvent event) {

    }

    /**
     * Window Event
     */
    @Override
    public void windowActivated(final WindowEvent event) {

    }

    /**
     * Window Event
     */
    @Override
    public void windowDeactivated(final WindowEvent event) {

    }
}

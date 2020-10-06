package de.urs.gui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.urs.data.LitHelper;
import de.urs.data.StaticUtility;

/**
 * Hauptmenu initalisieren
 *
 * @author urs
 *
 */
public class Hauptmenu extends JFrame implements ActionListener {
    /**
     * Logger dieser Klasse
     */
    private static final Logger LOG = LogManager.getLogger(Hauptmenu.class);
    /**
     * Serial ID
     */
    private static final long serialVersionUID = -3193596188444559215L;

    /**
     * Startbutton
     */
    /* default */ private final JButton btnStart; // NOPMD by urs on 08.04.20, 07:16

    /**
     * Spielladen Button
     */
    /* default */ private final JButton btnSpielLaden; // NOPMD by urs on 07.04.20, 08:08

    /**
     * Highscore Button
     */
    /* default */ private final JButton btnHighscore; // NOPMD by urs on 08.04.20, 07:16

    /**
     * Hilfbutton
     */
    /* default */ private final JButton btnHilfe; // NOPMD by urs on 08.04.20, 07:16

    /**
     * Create the frame.
     */
    public Hauptmenu() {
        super();
        setTitle(StaticUtility.HAUPTMENUTITEL);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 369, 242);
        final JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setResizable(false);
        setVisible(true);

        // Labels
        final JLabel lblWirtsim = new JLabel(StaticUtility.NAME);
        lblWirtsim.setBounds(118, 5, 143, 15);

        // Buttons
        btnStart = new JButton(StaticUtility.BTNSTART);
        btnStart.setBounds(17, 47, 327, 25);
        btnStart.addActionListener(this);

        btnSpielLaden = new JButton(StaticUtility.BTNLADEN);
        btnSpielLaden.setBounds(17, 90, 327, 25);
        btnSpielLaden.addActionListener(this);

        btnHighscore = new JButton(StaticUtility.BTNSCORE);
        btnHighscore.setBounds(17, 133, 327, 25);
        btnHighscore.addActionListener(this);

        btnHilfe = new JButton(StaticUtility.BTNHILFE);
        btnHilfe.setBounds(17, 170, 327, 25);
        btnHilfe.addActionListener(this);

        contentPane.setLayout(null);
        contentPane.add(btnStart);
        contentPane.add(btnSpielLaden);
        contentPane.add(btnHighscore);
        contentPane.add(btnHilfe);
        contentPane.add(lblWirtsim);

        LOG.debug("GUI OK");
    }

    /**
     * Spiel starten Methode
     *
     * @since 1.0
     */
    private void startSpiel() {
        LOG.debug("startSpiel");
        dispose();
        new SpielScreen(true);
    }

    /**
     * Spiel laden Methode
     *
     * @since 1.5
     */
    private void loadGame() {
        LOG.debug("loadGame");
        dispose();
        new SpielScreen(false);
    }

    /**
     * High Score anzeigen - not implemented
     *
     * @since 2.0
     */
    private void loadHighScore() {
        LOG.debug("loadHighScore");
        final Desktop desk = Desktop.getDesktop();
        try {
            desk.browse(new URI(LitHelper.URL + "index.php?id=Score"));
        } catch (final IOException e) {
            LOG.error("loadHighScore @ Hauptmenu (IOException): ", e);
        } catch (final URISyntaxException e) {
            LOG.error("loadHighScore @ Hauptmenu (URISyntaxException): ", e);
        }
    }

    /**
     * Hilfe laden - not implemented
     *
     * @since 2.0
     */
    private void loadHelp() {
        LOG.debug("loadHelp");
        final Desktop desk = Desktop.getDesktop();
        try {
            desk.browse(new URI(LitHelper.URL + "index.php?id=Hilfe"));
        } catch (final IOException e) {
            LOG.error("loadHelp @ Hauptmenu (IOException): ", e);
        } catch (final URISyntaxException e) {
            LOG.error("loadHelp @ Hauptmenu (URISyntaxException): ", e);
        }
    }

    /**
     * Action Listener Verwaltung
     *
     * @since 3.0
     */
    @Override
    public void actionPerformed(final ActionEvent event) {
        if (event.getSource() == btnStart) {
            startSpiel();
        } else if (event.getSource() == btnSpielLaden) {
            loadGame();
        } else if (event.getSource() == btnHilfe) {
            loadHelp();
        } else if (event.getSource() == btnHighscore) {
            loadHighScore();
        }
    }
}

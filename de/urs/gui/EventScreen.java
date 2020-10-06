package de.urs.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.urs.data.StaticUtility;

/**
 * EventScreen
 *
 * @author urs
 *
 */
public class EventScreen extends JFrame implements ActionListener {
    /**
     * Logger dieser Klasse
     */
    private static final Logger LOG = LogManager.getLogger(EventScreen.class);

    /**
     * Serial ID
     */
    private static final long serialVersionUID = 2603241915629187323L;

    /**
     * Create the frame.
     */
    public EventScreen(final String inhalt) {
        super();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 893, 156);
        final JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setVisible(true);
        setAlwaysOnTop(true);
        setTitle(StaticUtility.NAME + " " + StaticUtility.EVENT);
        contentPane.setLayout(null);

        final JButton btnOk = new JButton(StaticUtility.BTNOK);
        btnOk.setBounds(5, 93, 875, 25);
        contentPane.add(btnOk);
        btnOk.addActionListener(this);

        final JLabel lblInhalt = new JLabel(inhalt);
        lblInhalt.setBounds(5, 20, 875, 73);
        contentPane.add(lblInhalt);

        final JLabel lblMeldungen = new JLabel(StaticUtility.LBLRADIO2);
        lblMeldungen.setBounds(5, 5, 875, 15);
        lblMeldungen.setHorizontalAlignment(SwingConstants.CENTER);
        lblMeldungen.setForeground(Color.MAGENTA);
        contentPane.add(lblMeldungen);
    }

    /**
     * ActionListener Verwaltung
     */
    @Override
    public void actionPerformed(final ActionEvent event) {
        LOG.debug("actionPerformed [close]");
        dispose();
    }
}

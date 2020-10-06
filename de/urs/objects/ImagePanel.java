package de.urs.objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.urs.data.LitHelper;

/**
 * Ein Bild als Hintergrund nehmen - JPANEL extension
 *
 * @author urs
 *
 */
public class ImagePanel extends JPanel {
    /**
     * Logger dieser Klasse
     */
    private static final Logger LOG = LogManager.getLogger(ImagePanel.class);
    /**
     * Serial ID
     */
    private static final long serialVersionUID = 1490752812704301363L;
    /**
     * Image Speicher
     */
    private BufferedImage image;

    /**
     * Konstruktor
     */
    public ImagePanel() {
        try {
            image = ImageIO.read(new URL(LitHelper.URL + "img/demo_background.png"));

        } catch (final IOException ex) {
            LOG.error(ex);
        }
    }

    /**
     * Interface implication
     */
    @Override
    protected void paintComponent(final Graphics grahics) {
        super.paintComponent(grahics);
        grahics.drawImage(image, 0, 0, this);
    }

}
package de.urs.logic;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.urs.conf.ConfigUtility;
import de.urs.data.DataID;
import de.urs.data.LitHelper;
import de.urs.data.NamenHelper;

/**
 * Arbeiter Klasse
 *
 * @author urs
 *
 */
public class Arbeiter implements Serializable {
    /**
     * Logger dieser Klasse
     */
    private static final Logger LOG = LogManager.getLogger(Arbeiter.class);
    /**
     * Serial ID
     */
    private static final long serialVersionUID = 165732863081277640L;

    /**
     * Stimmungen der Arbeiter
     *
     * @author urs
     *
     */
    public enum StimmungTypes {
        GUT, NORMAL, SCHLECHT, SCHWANGER, NICHTARBEITEND, URLAUB, KRANK;
    }

    /**
     * Stimmung der Arbeiter
     */
    private StimmungTypes arbeiterStimmung;
    /**
     * Position des Arbeiters
     */
    private String position;
    /**
     * Name des Arbeiters
     */
    private String name;
    /**
     * Anrede des Arbeiters
     */
    private String anrede;
    /**
     * ID des Arbeiters
     */
    private String workerID; // NOPMD by urs on 07.04.20, 09:16

    /**
     * Konstruktor, um dem Chef zu erstellen.
     *
     * @param a
     */
    public Arbeiter() {
        LOG.info("neuer Arbeiter eingestellt");

        // Stimmung des neuen Mitarbeiter - entweder normal oder gut, weil endlich ein
        // neuer Job
        final int moodzufall = ConfigUtility.zufallInt(2, 1);
//        Stimmung des neuen Arbeiters
        if (moodzufall == LitHelper.ONE) {
            arbeiterStimmung = StimmungTypes.NORMAL;
        } else if (moodzufall == LitHelper.TWO) {
            arbeiterStimmung = StimmungTypes.GUT;
        } else {
            arbeiterStimmung = StimmungTypes.SCHLECHT;
        }
        // Zufälligen Namen auswaählen von den 117? Namen in der Namensliste
        final int zufallNamen = ConfigUtility.zufallInt(NamenHelper.getNamenSize(), 0);
        setName(NamenHelper.getNamen(zufallNamen));

        // Geschlecht herausfinden + setzen | (m/w/d)
        final int geschlecht = ConfigUtility.zufallInt(3, 0);
        setAnrede(NamenHelper.getAnreden(geschlecht));
        position = NamenHelper.getPosition(geschlecht);

        setId(DataID.generateArbeiterID());
    }

    /**
     * Chef Konstruktur
     *
     * @param i
     */
    public Arbeiter(final int i) { // NOPMD by urs on 06.04.20, 15:06
        LOG.info("Chef erstellt");
        if (i == LitHelper.ZERO) {
            arbeiterStimmung = StimmungTypes.NORMAL;
            position = "Chef";

            final int zufallNamen = ConfigUtility.zufallInt(NamenHelper.getNamenSize(), 0);
            setName(NamenHelper.getNamen(zufallNamen));

            setAnrede(NamenHelper.getAnreden(0));
            setId(DataID.generateArbeiterID());
        }
    }

    /**
     * Gibt die Anzahl der vom Arbeiter verarbeitbarer Rohstoffe
     *
     * @return produzierbare Anzahl
     */
    public int getProduzieren() {
        int produzierbar;
        switch (arbeiterStimmung) {
        case GUT:
            produzierbar = 6;
            break;
        case NORMAL:
            produzierbar = 4;
            break;
        case SCHLECHT:
            produzierbar = 2;
            break;
        default:
            produzierbar = 0;
            break;
        }
        return produzierbar;
    }

    /**
     * Gibt die Stimmung der Arbeiter zurück
     *
     * @return Stimmung
     */
    public StimmungTypes getArbeiterStimmung() {
        return arbeiterStimmung;
    }

    /**
     * Gibt die Stimmung des Arbeiters zurück (als String)
     *
     * @return arbeiterStimmung
     */
    public final String getArbeiterStimmungString() {
        String text;
        switch (arbeiterStimmung) {
        case GUT:
            text = "gut";
            break;
        case NORMAL:
            text = "normal";
            break;
        case SCHLECHT:
            text = "schlecht";
            break;
        case SCHWANGER:
            text = "schwanger";
            break;
        case NICHTARBEITEND:
            text = "nicht am Arbeiten";
            break;
        case URLAUB:
            text = "im Urlaub";
            break;
        default:
            text = "";
            LOG.warn("Default Option @ getArbeiterStimmungString");
            break;
        }
        return text;
    }

    /**
     * setzt die Stimmung der Arbeiter
     *
     * @param arbeiterStimmung
     */
    public void setArbeiterStimmung(final StimmungTypes arbeiterStimmung) {
        this.arbeiterStimmung = arbeiterStimmung;
    }

    /**
     * Erniedrigt die Stimmung des Arbeiters
     */
    public void subtrakhiereStimmung() {
        switch (arbeiterStimmung) {
        case GUT:
            setArbeiterStimmung(Arbeiter.StimmungTypes.NORMAL);
            break;
        case NORMAL:
            setArbeiterStimmung(Arbeiter.StimmungTypes.SCHLECHT);
            break;

        default:
            LOG.info("Default Option @ subtrakhiereStimmung");
            break;
        }
    }

    /**
     * Erhöht die Stimmung des Arbeiters
     *
     */
    public void addiereStimmung() {
        switch (arbeiterStimmung) {
        case NORMAL:
            setArbeiterStimmung(Arbeiter.StimmungTypes.GUT);
            break;
        case SCHLECHT:
            setArbeiterStimmung(Arbeiter.StimmungTypes.NORMAL);
            break;
        default:
            LOG.info("Default Option @ addiereStimmung");
            break;
        }
    }

    /**
     * Gibt die Position des Arbeiters zurück
     *
     * @return Position des Arbeiters
     */
    public String getPosition() {
        return position;
    }

    /**
     * Setzt die Position des Arbeiter. zB. Chef/Arbeiter/Vorarbeiter
     *
     * @param position
     */
    public void setPosition(final String position) {
        this.position = position;
    }

    /**
     *
     * @return Name des Arbeiters
     */
    public final String getName() {
        return name;
    }

    /**
     * Setzt den Namen eines Arbeiters
     *
     * @param name
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Holt die Anrede
     *
     * @return
     */
    public final String getAnrede() {
        return anrede;
    }

    /**
     * Setzt die Anrede
     *
     * @param anrede
     */
    public final void setAnrede(final String anrede) {
        this.anrede = anrede;
    }

    /**
     * holt die ID
     *
     * @return
     */
    public String getId() {
        return workerID;
    }

    /**
     * Setzt die ID
     *
     * @param identiCode
     */
    public final void setId(final String identiCode) {
        workerID = identiCode;
    }

}
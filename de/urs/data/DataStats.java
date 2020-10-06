package de.urs.data; // NOPMD by urs on 07.04.20, 07:46

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import de.urs.conf.ConfigUtility;
import de.urs.logic.Arbeiter;
import de.urs.logic.Arbeiter.StimmungTypes;

/**
 * Klasse zum Spiechern von Werten
 */
public class DataStats implements Serializable { // NOPMD by urs on 07.04.20, 07:47
    /**
     * Serial ID
     */
    private static final long serialVersionUID = -3463016062351963007L;

    /**
     * Phasenzähler
     */
    private BigInteger phasenzaehler = BigInteger.ZERO; // NOPMD by urs on 06.04.20, 14:20
    /**
     * Ist jetzt die VerkaufsPhase
     */
    private boolean verPhase = true; // NOPMD by urs on 06.04.20, 14:25
    /**
     * Eventvorteil
     */
    private int eventVorteil = 0; // NOPMD by urs on 06.04.20, 14:20

    /**
     * Preis Config: einkaufPreis
     */
    private double einkaufPreis = 0; // NOPMD by urs on 06.04.20, 14:20
    /**
     * Preis Config: verkaufPreis
     */
    private double verkaufPreis = 0; // NOPMD by urs on 06.04.20, 14:20

    /**
     * Event Manager: nextBigEventin
     */
    private int nextBigEventin = 0; // NOPMD by urs on 06.04.20, 14:24
    /**
     * Event Manager: nextEventBigKind
     */
    private int nextEventBigKind = 0; // NOPMD by urs on 06.04.20, 14:20
    /**
     * Event Manager: nextLittleEventin
     */
    private int nextLittleEventin = 0; // NOPMD by urs on 06.04.20, 14:20
    /**
     * Event Manager: nextEventLittleKind
     */
    private int nextEventLittleKind = 0; // NOPMD by urs on 06.04.20, 14:20

    /**
     * Mitarbeiter Liste
     */
    private List<Arbeiter> mitarbeiter = new ArrayList<>();

    /**
     * Vermögen im Spiel
     */
    private BigDecimal kapital = new BigDecimal("20"); // NOPMD by urs on 06.04.20, 14:20
    /**
     * Umsatz pro Periode
     */
    private BigDecimal umsatz = BigDecimal.ZERO; // NOPMD by urs on 06.04.20, 14:20

    /**
     * Anzahl der Produkte
     */
    private int produkte = 0; // NOPMD by urs on 07.04.20, 07:55
    /**
     * Anzahl der Rohstoffe
     */
    private int rohstoffe = 2; // NOPMD by urs on 07.04.20, 07:55
    /**
     * Logik
     */
    private int schongekauft = 0; // NOPMD by urs on 07.04.20, 07:56
    /**
     * Logik
     */
    private int schonherrgestellt = 0; // NOPMD by urs on 07.04.20, 07:56

    /**
     * Score
     */
    private BigInteger score = BigInteger.ZERO; // NOPMD by urs on 07.04.20, 07:55
    // ------------------------------------------------------ //

    /**
     * Konstruktor der Stats-Objekt
     */
    public DataStats() {
        verPhase = true;
    }

    // ------------------------------------------------------ //

    /**
     *
     * @return Phasenzaehler
     */
    public int getPhasenzaehler() {
        return phasenzaehler.intValue();
    }

    /**
     * Phasenzahler um ein erhoehen
     */
    public void addOnePhasenzaehler() {
        phasenzaehler = phasenzaehler.add(BigInteger.ONE);
    }

    /**
     * Phasenzahler um ein erniedrigen
     *
     */
    public void fixZaehlerByLoad() {
        phasenzaehler = phasenzaehler.subtract(BigInteger.ONE);
        score = score.subtract(BigInteger.ONE);
    }

    // ------------------------------------------------------ //

    /**
     * Gibt an, in welcher Phase man ist: { true : Verkaufsphase },{ false :
     * Einkaufsphase}
     *
     * @return true/false
     */
    public boolean isVerkaufPhase() {
        return verPhase;
    }

    /**
     * Man setzt die Phase auf einen Wert.
     *
     * @param true/false
     */
    public void setVerkaufPhase(final boolean phase) {
        verPhase = phase;

    }

    // ------------------------------------------------------ //

    /**
     * Event Vorteil zurückgeben
     *
     * @return EventVorteil
     */
    public int getEventVorteil() {
        return eventVorteil;
    }

    /**
     * Event Vorteil setzen
     *
     * @param eventVorteil
     */
    public void setEventVorteil(final int eventVorteil) {

        this.eventVorteil = eventVorteil;
    }

    // ------------------------------------------------------ //

    /**
     * Gibt ein Integer aus, bei dem das nächste Event startet.
     *
     * @return nextEventin
     */
    public int getNextBigEventin() {
        return nextBigEventin;
    }

    /**
     * Gibt ein Integer aus, bei dem das nächste Event startet.
     *
     * @return nextEventin
     */
    public int getNextLittleEventin() {
        return nextLittleEventin;
    }

    /**
     * Setzt den Zähler für das nächste Event ein
     *
     * @param nextEventin
     */
    public void setNextBigEventin(final int nextEventin) {
        nextBigEventin = nextEventin;
    }

    /**
     * Setzt den Zähler für das nächste Event ein
     *
     * @param nextEventin
     */
    public void setNextLittleEventin(final int nextEventin) {
        nextLittleEventin = nextEventin;
    }

    /**
     * Art des nächsten Events holen
     *
     * @return ID
     */
    public int getNextEventBigKind() {
        return nextEventBigKind;
    }

    /**
     * Art des nächsten Events setzen
     *
     * @param nextEventBigKind
     */
    public void setNextEventBigKind(final int nextEventBigKind) {
        this.nextEventBigKind = nextEventBigKind;
    }

    /**
     * Art des nächsten Events holen
     *
     * @return ID
     */
    public int getNextEventLittleKind() {
        return nextEventLittleKind;
    }

    /**
     * Art des nächsten Events setzen
     *
     * @param nextEventLittleKind
     */
    public void setNextEventLittleKind(final int kind) {
        nextEventLittleKind = kind;
    }

    // ------------------------------------------------------ //

    /**
     * Gibt an, ob es zurzeit die Verkaufsphase ist
     *
     * @return
     */
    public boolean isVerkaufsphase() {
        return verPhase;
    }

    /**
     * Setzt die Verkaufsphase
     *
     * @param phase
     */
    public void setVerkaufsphase(final boolean phase) {
        verPhase = phase;
    }

    // ------------------------------------------------------ //

    /**
     * Gibt List<Arbeiter> zurück
     *
     * @return Mitarbeiter
     */
    public List<Arbeiter> getMitarbeiter() {
        return mitarbeiter;
    }

    // ------------------------------------------------------ //

    /**
     * Fügt ein neuen Arbeiter hinzu
     */
    public void addArbeiter() {
        mitarbeiter.add(new Arbeiter());
    }

    /**
     * Entlässt ein Arbeiter
     *
     * @param lblFehler
     */
    public void removeArbeiter(final JLabel lblFehler) {
        int arrzah = LitHelper.ONE; // NOPMD by urs on 07.04.20, 08:07
        for (final Arbeiter ar : getMitarbeiter()) {
            if (ar.getArbeiterStimmung() != StimmungTypes.KRANK // NOPMD by urs on 07.04.20, 07:54
                    || ar.getArbeiterStimmung() != StimmungTypes.SCHWANGER
                    || !ar.getPosition().equalsIgnoreCase("Chef")) { // NOPMD by urs on 09.04.20, 15:06
                mitarbeiter.remove(arrzah);
                return;
            } else {
                lblFehler.setText(StaticUtility.ERRKRANKARB);
            }
            arrzah += 1;
        } // end of for

    }

    /**
     * Eine Mitarbeiterliste setzen
     *
     * @param mitarbeiter Liste
     */
    public void setMitarbeiter(final List<Arbeiter> mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    // ------------------------------------------------------ //

    // Kapital Funktionen
    /**
     * Gibt das Vermögen des Betriebes zurück
     *
     * @return kapital
     */
    public double getKapital() {
        norm();
        return kapital.doubleValue();
    }

    /**
     * Erhöht das Vermögen
     *
     * @param einnahmen
     */
    public void addKapital(final double temp) {
        kapital = kapital.add(new BigDecimal(new StringBuilder().append(temp).toString()).abs());
    }

    /**
     * Erniedrig das Vermögen
     *
     * @param kosten
     */
    public void removeKapital(final double kapitals) {
        kapital = kapital.subtract(new BigDecimal(new StringBuilder().append(kapitals).toString()).abs());
    }

    /**
     * Erniedrig das Vermögen
     *
     * @param kosten
     */
    public void removeKapital(final int kapitals) {
        kapital = kapital.subtract(new BigDecimal(new StringBuilder().append(kapitals).toString()).abs());
    }

    /**
     * Gibt den Umsatz des Unternehmens zurück
     *
     * @return Umsatz
     */
    public double getUmsatz() {
        norm();
        return umsatz.doubleValue();
    }

    /**
     * Addiert den Umsatz
     *
     * @param Umsatz
     */
    public void addUmsatz(final double umsatz) {
        this.umsatz = this.umsatz.add(new BigDecimal(new StringBuilder().append(umsatz).toString()).abs());
    }

    /**
     * Normt den Umsatz & Kapital
     */
    private void norm() {
        final int anzahlU = new StringBuilder().append(umsatz.intValue()).toString().length() + 2;
        umsatz = umsatz.round(new MathContext(anzahlU, RoundingMode.HALF_UP));
        final int anzahlK = new StringBuilder().append(kapital.intValue()).toString().length() + 2;
        kapital = kapital.round(new MathContext(anzahlK, RoundingMode.HALF_UP));
    }

    // ------------------------------------------------------ //

    /**
     * Gibt die anzahl der Produkte zurück
     *
     * @return Produkte
     */
    public int getProdukte() {
        return produkte;
    }

    /**
     * Fügt Produkte hinzu
     *
     * @param produkte
     */
    public void addProducts(final int produkte) {
        this.produkte += produkte;
    }

    /**
     * Entfernt Produkte
     *
     * @param produkte
     */
    public void removeProdukte(final int produkte) {
        this.produkte -= produkte;
    }

    /**
     * Gibt die Anzahl der Rohstoffe zurück
     *
     * @return Rohstoffe
     */
    public int getRohstoffe() {
        return rohstoffe;
    }

    /**
     * Erhöht die Anzahl der Rohstoffe
     *
     * @param rohstoffe
     */
    public void addRohstoffe(final int rohstoffe) {
        this.rohstoffe += rohstoffe;
    }

    /**
     * Erniedrig die Anzahl der Rohstoffe
     *
     * @param rohstoffe
     */
    public void removeRohstoffe(final int rohstoffe) {
        this.rohstoffe -= rohstoffe;
    }

    // ------------------------------------------------------ //

    /**
     * Gibt die anzahl zurück, wie viel der Spieler gekauft hat
     *
     * @return schongekauft
     */
    public int getSchongekauft() {
        return schongekauft;
    }

    /**
     * Setzt die Anzahl der schongekauften auf eine Zahl
     *
     * @param schongekauft
     */
    public void setSchongekauft(final int schongekauft) {
        this.schongekauft = schongekauft;
    }

    /**
     * Addiert schongekauft um eine Zahl
     *
     * @param schongekauft
     */
    public void addSchonGekauft(final int schongekauft) {
        this.schongekauft += schongekauft;
    }

    /**
     * Wieviel Rohstoffe hat der Spieler schon umgewandelt.
     *
     * @return schonherrgestellt
     */
    public int getSchonherrgestellt() {
        return schonherrgestellt;
    }

    /**
     * Setzt, wie viel der Spieler schon umgewandelt hat
     *
     * @param schonherrgestellt
     */
    public void setSchonherrgestellt(final int zahl) {
        schonherrgestellt = zahl;
    }

    /**
     * Addiert die Menge der schon umgewandeltete Produkte
     *
     * @param schonherrgestellt
     */
    public void addSchonherrgestellt(final int zahl) {
        schonherrgestellt += zahl;
    }

    // ------------------------------------------------------ //

    /**
     * Nach dem Steuerbescheid, wird der Umsatz resettet.
     */
    public void resetSteuerUmsatz() {
        umsatz = BigDecimal.ZERO;
    }

    /**
     * Holung des Einkaufspreis
     *
     * @return Einkaufspreis
     */
    public double getEinkaufPreis() {
        return einkaufPreis;
    }

    /**
     * Man setzt hiermit den Einkaufspreis
     *
     * @param Preis
     */
    public void setEinkaufPreis(final double preis) {
        einkaufPreis = ConfigUtility.roundbetter(preis);
    }

    /**
     * Einkaufspreis generieren
     */
    public void genEinkaufspreis() {
        setEinkaufPreis(ConfigUtility.zufallDouble(ConfigUtility.ABGEDECKTROH, ConfigUtility.UNTERROH));
    }

    // Verkauf
    /**
     * Verkaufspreis generieren
     */
    public void genVerkaufspreis() {
        setVerkaufPreis(ConfigUtility.zufallDouble(ConfigUtility.ABGEDECKTPRO, ConfigUtility.UNTERPRO));
    }

    /**
     * Berechnet dem Maximalen Einkauf Vermögen/200 = Einkaufbare Rohstoffe
     * (ganzzeilige Division)
     *
     * @param Betrieb
     * @return ganze Zahl / Maximaler Einkauf
     */
    public int maximumEinkaufs() {
        int maxEinkauf = 2;
        maxEinkauf += (int) getKapital() / ConfigUtility.NEUROHSCHWE;
        return maxEinkauf;
    }

    /**
     *
     * @return Produzierkosten
     */
    public double getProduzierKosten() {
        return 2.5;
    }

    /**
     *
     * @return Verkaufspreis
     */
    public double getVerkaufPreis() {
        return verkaufPreis;
    }

    /**
     * Den Verkaufspreis setzen
     *
     * @param verkaufPreis
     */
    private void setVerkaufPreis(final double preis) {
        verkaufPreis = ConfigUtility.roundbetter(preis);
    }

    /**
     * Score des Spielers
     *
     * @return score
     */
    public BigInteger getScore() {
        return score;
    }

    /**
     * Fügt einen Score den Score zu
     *
     * @param score
     */
    public void addScore(final BigInteger score) {
        this.score = this.score.add(score);
    }

}

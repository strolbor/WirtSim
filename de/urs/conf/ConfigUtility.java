package de.urs.conf;

/**
 * Klassen, zum spiechern von statischen Daten
 *
 * @author urs
 *
 */
public final class ConfigUtility { // NOPMD by urs on 08.04.20, 07:51
    /* statische Raten */
    /**
     * Rohstoff zu Produkte Rate
     */
    public static final int RZPRATE = 4;
    /**
     * Steuerphase
     */
    public static final int STEUERPHASE = 24; // 24/2 = 12 Runden
    /**
     * Gehaltphase
     */
    public static final int GEHALTPHASE = 12;// 10/2 = 5 Runden

    // Arbeiter
    /**
     * Arbeiter Lohn
     */
    public static final int WORKERFEE = 20;
    /**
     * Schwellen, das man 1 Rohstoff mehr kaufen kann
     */
    public static final int NEUROHSCHWE = 200;
    // Runden und Zufall Funktionen

    /* Event Manager */
    /**
     * Kleine Events Rundenanzahl mindestens --> Spannweite.
     */
    public static final int KLEINESEVENTMIN = 10;
    /**
     * Kleine Events Rundenanzahl Spannweite --> Anzahl der Zahlen
     */
    public static final int KLEINANZAHL = 16;
    /**
     * Große Events Spannweite. Mindest Zahl
     */
    public static final int GROSESEVENTMIN = 20;
    /**
     * Große Events Spannweite. Anzahl der Zahlen
     *
     */
    public static final int GROSSANZAHL = 25;

    // Preis einstellungen
    /**
     * Preis Config: untereGrenzeRohstoff
     */
    public static int UNTERROH = 1; // NOPMD by urs on 06.04.20, 14:20
    /**
     * Preis Config: abgedeckteZahlenAnzahlRohstoff
     */
    public static int ABGEDECKTROH = 15; // NOPMD by urs on 06.04.20, 14:24
    /**
     * Preis Config: untereGrenzeProdukt
     */
    public static int UNTERPRO = 1; // NOPMD by urs on 06.04.20, 14:20
    /**
     * Preis Config: abgedeckteZahlenAnzahlProdukt
     */
    public static int ABGEDECKTPRO = 15; // NOPMD by urs on 06.04.20, 14:25

    /**
     * Spannweite kleine Events. (Anzahl der einprogrammierten Events) = Zahl der
     * genrierten Zaheln. --> Kind of Event
     */
    public static final int LITLLERANGE = 5;
    /**
     * Spannweite großen Events. (Anzahl der einprogrammierten Events) = Zahl der
     * genrierten Zaheln. --> Kind of Event
     */
    public static final int BIGRANGE = 8;

    // ------------------------------------------------------ //

    /**
     * Mit roundbetter wird die double auf 2 Nachkommastellen gerundet
     *
     * @param value - Wert, der zu Runden ist
     * @return double mit 2 Nachkommastellen
     */
    public static double roundbetter(final double value) {
        final double roundto = Math.pow(10, 2);// 2 ist Anzahl der Decimalkommastellen
        return Math.round(roundto * value) / roundto;
    }

    /**
     * Generiert eine Zufalls Zahl
     *
     * @param Anzahl - Wieviele Zahlen abgedeckt werden
     * @param Start  - welche Zahl ist die erste?
     * @return eine Random integer
     */
    public static double zufallDouble(final int anzahl, final int start) {
        return roundbetter(Math.random() * anzahl + start);
    }

    /**
     * Generiert eine Zufalls Zahl
     *
     * @param Anzahl - Wieviele Zahlen abgedeckt werden
     * @param Start  - welche Zahl ist die erste?
     * @return eine Random integer
     */
    public static int zufallInt(final int anzahl, final int start) {
        return (int) roundbetter(Math.random() * anzahl + start);
    }

    // ------------------------------------------------------ //
    // TODO: Preisconfig

    /**
     * setzt die Preisspanne der Rohstoffe neu
     *
     * @param untereGrenzE
     * @param abgedeckteZahlenAnzahL
     */
    public static void setPreisspaRoh(final int untereGrenzE, final int abgedeckteZahlenAnzahL) { // NOPMD by urs on
                                                                                                  // 06.04.20,
        // 14:24
        UNTERROH = untereGrenzE;
        setAbgedeckteZahlenAnzahlRohstoff(abgedeckteZahlenAnzahL);
    }

    /**
     * Für den Event Manager, die den Bereich der Zufallszahlen generieren
     *
     * @param untereGrenzE           - welche Zahl ist das Mininum
     * @param abgedeckteZahlenAnzahL - Wieviele Zahlen werden bedient
     */
    public static void setPreisspaPro(final int untereGrenze, final int abgedeckteZahlen) {
        UNTERPRO = untereGrenze;
        ABGEDECKTPRO = abgedeckteZahlen;
    }

    /**
     * Anzahl der abgedeckten Zahlen bei den Rohstoffen setzen
     *
     * @param ABGEDECKTROH
     */
    public static void setAbgedeckteZahlenAnzahlRohstoff(final int abgedeckteZahlen) {
        ABGEDECKTROH = abgedeckteZahlen;
    }

    /**
     * Untere Grenze von Rohstoffen setzen
     *
     * @param untereGrenzeRohstoff
     */
    public static void setUntereGrenzeRohstoff(final int untereGrenze) {
        UNTERROH = untereGrenze;
    }

    /**
     * Untere Grenze der Produkte setzen
     *
     * @param untereGrenzeProdukt
     */
    public static void setUntereGrenzeProdukt(final int untereGrenze) {
        UNTERPRO = untereGrenze;
    }

    /**
     * Normalisieren Preispannen
     */
    public static void normalizeAll() {
        setPreisspaPro(UNTERPRO, ABGEDECKTPRO);
        setPreisspaRoh(UNTERROH, ABGEDECKTROH);
    }

    // ------------------------------------------------------ //
    /**
     * Konstruktor, privat
     */
    private ConfigUtility() {

    }

}

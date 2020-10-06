package de.urs.data;

/**
 * Eine Klasse mit den ganzen Namen, sowie die erfundenen Personendaten
 *
 * @author urs
 *
 */
public final class NamenHelper { // NOPMD by urs on 07.04.20, 07:47
    /**
     * Eingespeicherte Anreden für m/w/d
     */
    private static final String[] ANREDEN = { "Herr", "Frau", "Person" };
    /**
     * Eingespeicherte Positionen für m/w/d
     */
    private static final String[] POSITION = { "Arbeiter", "Arbeiterin", "Arbeitenden" };
    /**
     * Es stehen 116 Namen zur Auswahl.
     */
    private static final String[] NAMEN = { "Aierstock", "Altmann", "Armstrong", "Bachseitz", "Bardens", "Basler",
            "Beier", "Bernotas", "Bilici", "Bohmer", "Braendlin", "Brockmoeller", "Buerfeindt", "Candemir", "Cimrman",
            "Cordsen", "Darmstaetter", "Dethard", "Dinkel", "Doering", "Duerr", "Eigenbrot", "Erasmy", "Fahrenbruch",
            "Feyerabendt", "Fonken", "Fresen", "Fuchsbauer", "Gehrckens", "Gesell", "Goeben", "Gott", "Grimsel",
            "Grotjan", "Guelen", "Haensgen", "Hanschumacher", "Haubrich", "Heintzer", "Henskes", "Hielbig",
            "Hoefelmann", "Holtvoeth", "Hubbertz", "Jacobs", "Januscheit", "Joncker", "Juergensmann", "Kampschroeer",
            "Katzmartzyk", "Kilic", "Klauser", "Klingelschmitt", "Knechten", "Koelblin", "Kortbein", "Krabbe",
            "Kristal", "Kuefner", "Kurpiuhn", "Lampert", "Leisengang", "Lieshek", "Lokoszus", "Maegerl", "Maertensson",
            "Mayers", "Merrettig", "Mikolajzak", "Moertenhuber", "Navratik", "Niedergesaes", "Noellgen", "Oberkamp",
            "Omay", "Ozolin", "Peeters", "Pfaefflein", "Poggensee", "Rainhardt", "Reintjens", "Roedeler", "Rosengarten",
            "Rudeloff", "Saibel", "Schafbauer", "Scherfenberg", "Schlueters", "Schnetzer", "Schouten", "Schuwardt",
            "Seible", "Seydelmann", "Simokat", "Spaenkuch", "Stankuhn", "Stelzel", "Stoecks", "Strunck", "Szymzsak",
            "Thees", "Tiedemann", "Treutmann", "Urbahnke", "Vennenkoetter", "Voelkerding", "von Nitzsch", "Walbaum",
            "Wegmueller", "Weitkemper", "Widmeier", "Wils", "Woerndel", "Wosny", "Zachariae", "Zinngrebe" };

    /**
     * Anreden Array Größe
     *
     * @return int soze of Anreden
     */
    public static int getAnredenSize() {
        return ANREDEN.length;
    }

    /**
     * Namen Array holen
     *
     * @return int size of Namen
     */
    public static int getNamenSize() {
        return NAMEN.length;
    }

    /**
     * Eine Anrede aus den Array holen
     *
     * @param was
     * @return String Anrede
     */
    public static String getAnreden(final int wasi) {
        int was = wasi;
        if (was > getAnredenSize()) {
            was = getAnredenSize();
        }
        return ANREDEN[was];
    }

    /**
     * Einen Namen aus den Array holen
     *
     * @param was
     * @return String Name
     */
    public static String getNamen(final int wasi) {
        int was = wasi;
        if (was > getNamenSize()) {
            was = getNamenSize();
        }
        return NAMEN[was];
    }

    /**
     * eine Position aus den Array holen
     *
     * @param was
     * @return String Position
     */
    public static String getPosition(final int wasi) { // NOPMD by urs on 07.04.20, 07:48
        int was = wasi;
        if (was > LitHelper.TWO) {
            was = 2;
        }
        return POSITION[was];
    }

}

package de.urs.data;

import java.math.BigInteger;

import de.urs.conf.ConfigUtility;

/**
 * Hier sind die Sprachdaten gesammetl
 *
 * @author urs
 *
 */
public class StaticUtility { // NOPMD by urs on 07.04.20, 07:53
    // TODO: Allg. Info
    /**
     * Allgemeine Infos
     */
    public static final String TEXT = "[Spiel] ";
    /**
     * Allgemeine Infos Version
     */
    public static final String VERSION = "3.5 BETA";
    /**
     * Allgemeine Infos Autor
     */
    public static final String AUTHOR = "Urs-Benedict Braun";
    /**
     * Allgemeine Infos Name
     */
    public static final String NAME = "Wirtschaftssimulator";

    /**
     * keine kranke Mitarbeiter entlassen
     */
    public static final String ERRKRANKARB = "Sie können keine kranke oder schwangere Mitarbeiter entlassen.";

    /**
     * Kein Produkt hergestellt
     */
    public static final String KEINPRODUKT = "Sie haben bislang keine Produkte hergestellt.";
    /**
     * Allgemeine Infos Rohstoff Namen
     */
    public static final String ROHSTOFFE = "Rohstoffe: ";
    /**
     * Allgemeine Infos Produkt Namen
     */
    public static final String PRODUKTE = "Produkte: ";
    /**
     * Allgemeine Infos Währung
     */
    public static final String WAEHRUNG = "€";
    /**
     * Allgemeine Infos [Einkauf]
     */
    public static final String EINKAUFSPHASE = "[Einkauf]";
    /**
     * Allgemeine Infos Phase
     */
    public static final String PHASE = "Phase: ";

    // TODO: Hauptmenu
    /**
     * Hauptmenu
     */
    public static final String HAUPTMENU = "Hauptmenue";
    /**
     * Hauptmenü Titel
     */
    public static final String HAUPTMENUTITEL = new StringBuilder().append(NAME).append(" - ").append(HAUPTMENU)
            .toString();
    /**
     * Hauptmenu Spiel starten
     */
    public static final String BTNSTART = "Spiel starten";
    /**
     * Hauptmenu Spiel laden
     */
    public static final String BTNLADEN = "Spiel laden";
    /**
     * Hauptmenu Highscore
     */
    public static final String BTNSCORE = "Highscore";
    /**
     * Hauptmenu Hilfe
     */
    public static final String BTNHILFE = "Hilfe";
    /**
     * Hauptmenu Optionen
     */
    public static final String BTNOPTIO = "Optionen";

//TODO: Verloren
    /**
     * Verloren
     */
    public static final String VERLOREN = "Schade. Sie haben verloren. Ihr Score kann gespeichert werden.";
    /**
     * Verloren Score veröffentlichen?
     */
    public static final String SCOREVEROEFF = "Wollen Sie Ihr Score veröffentlichen? Dabei willigen Sie ein, dass wir Ihren Namen und Ihren Score veröffentlichen dürfen. Es gelten unsere Datenschutzbestimmungen.";
    /**
     * Verloren Titel JOptionPane
     */
    public static final String EINVERSCORE = "Einverständnis zur Veröffentlichung";
    /**
     * Verloren Input: Name
     */
    public static final String INPUTNAME = "Dann geben Sie bitte ein (Nick-)Namen ein. ";
    /**
     * Verloren Score veröffentlicht
     */
    public static final String SCOREVEROEFF2 = "Ihr Score wurde veröffentlicht.";
    /**
     * Verloren Score ist
     */
    public static final String SCOREIST = "Ihr Score beträgt: ";
    /**
     * Verloren Insolvenz
     */
    public static final String INSOLVENT = "Insolvenz gehen";

    // TODO: Screen
    /**
     * Screen Label Arbeiter
     */
    public static final String LBLARBEITER = "Arbeiter";
    /**
     * Screen Label Stimmung
     */
    public static final String LBLSTIMMUNG = "Ø-Stimmung";
    /**
     * Screen Label Kapital
     */
    public static final String LBLKAPITAL = "Kapital";
    /**
     * Screen Label Umsatz
     */
    public static final String LBLUMSATZ = "Umsatz";
    /**
     * Screen Label Menge
     */
    public static final String LBLMENGE = "Menge: ";
    /**
     * Screen Label Steuerinformationen
     */
    public static final String LBLSTEUERINFO = "Steuerinformationen";
    /**
     * Screen Label Meldungen
     */
    public static final String LBLRADIO = "Meldungen";
    /**
     * Screen Label Meldung
     */
    public static final String LBLRADIO2 = "Meldung";
    /**
     * Screen Label Preis
     */
    public static final String LBLPREIS = "Preis";
    /**
     * Screen Label Fehler
     */
    public static final String LBLFEHLER = "Fehler";
    /**
     * Screen Label Umsatzsteuer
     */
    public static final String LBLUMSATZSTEUER = "Umsatzsteuer";
    /**
     * Screen Label SteuerInfo
     */
    public static final String LBLSTEUERINFO2 = new StringBuilder().append("Die Steuern sind alle ")
            .append(ConfigUtility.STEUERPHASE).append(" Runden zu bezahlen!").toString();
    /**
     * Screen Label Steuer
     */
    public static final String LBLKOERPERSTEUER = "Körperschaftsteuer";
    /**
     * Screen Label Steuer
     */
    public static final String LBLGEWERBESTEUER = "Gewerbesteuer";
    /**
     * Screen Label Steuer
     */
    public static final String LBLARBEITEROPTIO = "Arbeiteroptionen";
    /**
     * Screen Label Arbeiter Info
     */
    public static final String LBLARBEITERINFO = "Arbeiter-Info";
    /**
     * Screen Label Anzahl
     */
    public static final String LBLANZAHL = "Anzahl: ";
    /**
     * Screen Label Gesamtpreis
     */
    public static final String LBLGESAMTPREIS = "Gesamtpreis: ";
    /**
     * Screen Label maxiamal verarbeitbare Rohstoffe
     */
    public static final String LBLMAXROHSTOFFE = "max. verarbeitbar Rohstoffe";
    /**
     * Screen Label Inventar
     */
    public static final String LBLINVENTAR = "Inventar";

    /**
     * Screen: Auftrag erteilen
     */
    public static final String BTNAUFTRAG = "Auftrag erteilen";
    /**
     * Screen: GoTo: Verkaufsphase
     */
    public static final String PHASTOVER = "GoTo: Verkaufsphase";
    /**
     * Screen: GoTo: Einkaufsphase
     */
    public static final String PHASTOEIN = "GoTo: Einkaufsphase";
    /**
     * Screen: Verkaufen
     */
    public static final String BTNVERKAUF = "Verkaufen";
    /**
     * Screen: Einkaufen
     */
    public static final String BTNEINKAUF = "Einkaufen";
    /**
     * Screen: Jetzt: Verkaufsphase
     */
    public static final String BTNVERKAUFNOW = "Jetzt: Verkaufsphase";
    /**
     * Screen: Jetzt: Einkaufsphase
     */
    public static final String BTNEINKAUFNOW = "Jetzt: Einkaufsphase";
    /**
     * Screen: Steuer Info
     */
    public static final String JTASTEUER01 = new StringBuilder().append(LBLSTEUERINFO2).append("\n")
            .append(LBLUMSATZSTEUER).append("\n").append(LBLKOERPERSTEUER).append("\n").append(LBLGEWERBESTEUER)
            .toString();

    // TODO: Eventscreen
    /**
     * Eventscreen Label Event
     */
    public static final String EVENT = "Event";
    /**
     * Eventscreen Label Inhalt
     */
    public static final String LBLINHALT = "Inhalt";
    /**
     * Eventscreen Button OK
     */
    public static final String BTNOK = "OK";

    // TODO: Arbeiter
    /**
     * Arbeiter Arbeiter kosten Geld Information
     */
    public static final String ARBGELD = new StringBuilder().append("Arbeiter einstellen kostet: ")
            .append(ConfigUtility.WORKERFEE).append(WAEHRUNG).append(" pro ").append(ConfigUtility.GEHALTPHASE)
            .append(" Phasen").toString();
    /**
     * Arbeiter Kosten
     */
    public static final String LBLGEHALTINFO = "Zurzeit kosten Sie dir: ";
    /**
     * Arbeiter: Info
     */
    public static final String LBLINFO = "Informationen";
    /**
     * Arbeiter: Kein Geld für neue Arbeiter
     */
    public static final String NOGELDARB = "Sie haben nicht genügend Geld um einen Arbeiter einzustellen.";
    /**
     * Arbeiter; Kein Chef kündbar
     */
    public static final String ONEWORKER = "Sie benötigen mindestens 1 Arbeiter.";
    /**
     * Arbeiter: Laufende Kosten
     */
    public static final String LAUFKOST = "Laufende Kosten";

    /**
     * Arbeiter: Anwerben
     */
    public static final String BTNANWERBEN = "1 Arbeiter anheuern";
    /**
     * Arbeiter: Entlassen
     */
    public static final String BTNFEUERN = "1 Arbeiter entlassen";
    /**
     * Arbeiter: schließen
     */
    public static final String BTNCLOSE = "Fenster schließen";

    // TODO: Fehler
    /**
     * Fehler: Positive zahlen eingeben
     */
    public static final String NOZAHLINPUT = "Bitte geben Sie nur positive Zahlen ein.";
    /**
     * Fehler: Kein Geld
     */
    public static final String LBLFEHRNOGELD = "Auftrag nicht ausgeführt. Sie haben kein Geld!";
    /**
     * Fehler: Insolvnt
     *
     */
    public static final String LBLFEHINSOL = "Bitte bezahlen Sie vorher Ihre Schulden oder gehen Sie Insolvent!";
    /**
     * Fehler: minuswerte
     */
    public static final String LBLFEHLNOMIN = "Keine Minuswerte erlaubt!";

    /**
     * Fehler: ID noch nicht erstellt ;)
     */
    public static final String UNKNOWNID = "Die ID ist mir nicht bekannt!";
    /**
     * Fehler: Lade Error
     */
    public static final String ERRORLOAD = "Konnte Spielstand nicht laden!";
    /**
     * Fehler: Schon gekauft
     */
    public static final String SCHONGEKAUFT = "Sie haben doch schon die Lager der Lieferanten ausgekauft";
    // Getter
    /**
     * Getter: ID weiterspielen
     */
    public static final String GETTERWEITER = "Wollen Sie mit der mir bekannten ID weiterspielen?";
    /**
     * Getter: laden
     */
    public static final String GETTERTITEL = "Spiel laden";
    /**
     * Getter: Input ID
     */
    public static final String GETTERIDINPUT = "Bitte geben Sie mir eine, mir bekannte, ID ein.";

    // TODO: Control
    /**
     * Control: Info Produkte hergestellt
     */
    public static final String LOGPRO = " Produkte hergestellt.";
    /**
     * Control Phasenwechsel 1
     */
    public static final String PHAWECHCON1 = new StringBuilder().append(BTNEINKAUFNOW).append("  | ").append(PHASTOVER)
            .toString();

    /**
     * Control PReisfestsetzen
     */
    public static final String VL2CON2 = new StringBuilder().append(BTNVERKAUFNOW).append("  | ").append(PHASTOEIN)
            .toString();

    /**
     * Control Eingabe Titel
     */
    public static final String INPUT = "Input-Dialog";
    /**
     * Control keine Rohstoffe mehr
     */
    public static final String NOROHSTOFFE = "Sie haben doch keine Rohstoffe mehr.";
    /**
     * Control keine Rohstoffe mehr zum produzieren
     */
    public static final String KEINEROHST = "Sie können keine Rohstoffe umwandeln, die sie nicht zur Verfügung haben.";
    /**
     * Control keine Arbeiter zum Auftrag erteilen
     */
    public static final String NOENOGHWORK = "Sie haben nicht genügend Arbeiter, um diese Anzahl der Rohstoffe umzuwandeln.";

    // TODO: Events
    /**
     * Event Pandemie
     */
    public static final String PANDEMIE = "Alle Mitarbeiter sind erkrankt. \nIhr Betrieb kommt zu erliegen.";
    /**
     * Event ProduktKnappheit
     */
    public static final String PRODUKTKNAPP = "Die Produkte sind nicht mehr nachgefragt,\n dass Sie es nicht mehr verkaufen können.";
    /**
     * Event Produkt Überschuss
     */
    public static final String PRODUKTUEBER = "Die Produkte sind mehr als nachgefragt.\n Die Kunden schlagen sich um Ihr Produkt.";
    /**
     * Event Rohstoff Knappheit
     */
    public static final String ROHSTOFFKNAPP = new StringBuilder().append("Zurzeit gibt es einen Rohstoffengpass.")
            .append("\nDeswegen erhöhen sich die Preise.").toString();

    // TODO: Methoden

    /**
     * Event Rohstoff Überschuss
     */
    public static final String ROHSTOFFUEBER = new StringBuilder().append("Zurzeit gibt es einen Rohstoffüberschuss.")
            .append("Deswegen sinken die Preise.").toString();
    /**
     * Event Spekulationsfreudig
     */
    public static final String SPEKULATION = new StringBuilder()
            .append("Sie leben außerhalb der Matrix, dadurch \nkönnen Sie pro Runde 5 Rohstoffe \n")
            .append("mehr einkaufen.\n").toString();

    /**
     * Event Steuer Nachzahlung
     *
     * @param kosten
     * @return String
     */
    public static String getSteuerNachzahlng(final double steuer) {
        return new StringBuilder().append("Sie müssen Steuer in der Höhe von ").append(steuer).append(" nachzahlen.")
                .toString();
    }

    /**
     * Event Steuer Erstattung
     *
     * @param kosten
     * @return String
     */
    public static String getSteuerErt(final double erstattung) {
        return new StringBuilder().append("Sie erhalten eine Steuerrückerstattung von: ").append(erstattung).toString();
    }

    /**
     * Event Schwangerschaft
     */
    public static final String getSchwanger(final String name) {
        return new StringBuilder().append("Ihre Arbeiterin ").append(name).append(" ist Schwanger und fällt damit aus.")
                .toString();
    }

    /**
     * Control baut den String auf
     *
     * @param kosten
     * @return String
     */
    public static String getWILLPRODU(final double kosten) {
        return new StringBuilder().append("Wie viel wollen Sie produzieren? Dies kostet Ihnen: ").append(kosten)
                .append(WAEHRUNG).append(" pro Stück.").toString();
    }

    /**
     * Control PReisfestsetzen
     */
    public static String getVL2(final double kosten) {
        return new StringBuilder().append(StaticUtility.LBLPREIS).append(": ").append(kosten).toString();
    }

    /**
     * Control baut den Preis auf
     *
     * @param einkaufspreis
     * @return String
     */
    public static String builderPreis(final double einkaufspreis) {
        return new StringBuilder().append(LBLPREIS).append(": ").append(einkaufspreis).toString();
    }

    /**
     * Control baut den getWIRKLICHPRODUZIEREN String auf
     *
     * @param useranzahl
     * @param kosten
     * @return String
     */
    public static String getWIRKLICHPROD(final int useranzahl, final double kosten) {
        return new StringBuilder().append("Wollen Sie wirklich ").append(useranzahl)
                .append(" Rohstoffe umwandeln? Das wird Ihnen insgesamt: ").append(kosten)
                .append(StaticUtility.WAEHRUNG).append(" kosten.").toString();
    }

    /**
     * [Verloren] Score ist
     */
    public static String getSCOREIST(final BigInteger score) {
        return new StringBuilder().append(SCOREIST).append(score.toString()).toString();
    }

    /**
     * Aufbau Service Krankheit Text beim Event
     *
     * @param anrede
     * @param name
     * @return
     */
    public static String getKRANKHEITTEXT(final String anrede, final String name) {
        return new StringBuilder().append(anrede).append(" ").append(name).append(" ist krank und fällt damit aus.")
                .toString();
    }

    /**
     * Aufbau Service Arbeiter Kündigungstext beim Event
     *
     * @param position
     * @param anrede
     * @param name
     * @return String
     */
    public static String getArbKuenTxt(final String position, final String anrede, final String name) {
        final StringBuilder builder = new StringBuilder(1024);
        if (anrede.equals(NamenHelper.getAnreden(0))) {
            // Herr
            builder.append("Ihr ");
        } else if (anrede.equals(NamenHelper.getAnreden(1))) {
            // Frau
            builder.append("Ihre ");
        }
        builder.append(position).append(" ").append(anrede).append(" ").append(name).append(" hat gekündigt."); // NOPMD
                                                                                                                // by
        // urs on
        // 07.04.20, 07:53
        return builder.toString();
    }

    // TODO: Betrieb
    /**
     * Betrieb baut den String auf
     *
     * @param kosten
     * @return String
     */
    public static String getArbBezahl(final double kosten) {
        return new StringBuilder().append("Sie haben Ihren Mitarbeiter ").append(kosten).append(" bezahlt.").toString();
    }

    // TODO: Steuer
    /**
     * Baut den Steuer String auf
     *
     * @param double umsatz
     * @param double koerpersteuer
     * @param double gewebersteuer
     * @return Steuer
     */
    public static String getSTEUERdata(final double umsatz, final double koerpersteuer, final double gewebersteuer) {
        return new StringBuilder().append(LBLSTEUERINFO2).append("\n").append(LBLUMSATZSTEUER).append(": ")
                .append(umsatz).append("\n").append(LBLKOERPERSTEUER).append(": ").append(koerpersteuer).append("\n")
                .append(LBLGEWERBESTEUER).append(": ").append(gewebersteuer).append("\n").toString();
    }
}

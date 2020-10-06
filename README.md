# Wirtschaftssimulator
Wirtschaftssimulator by Urs-Benedict Braun
Abteilung: Softwareentwicklung
In der Zeit von 9. März 2020 bis 10. April 2020

Urkonzept aus der Wirtschaftsinformatik der OvGu / IGS „Regine Hildebrandt“ (Schuljahr 2018/2019)

## Veränderung
* executeBuy @ Control.class:
	* Überprüfung der Schulden rausgenommen


## Fertig
* Kapital auf 10€ setzen in DataStats.java
* Events
	* Steuernachzahlung
	* Steuerrückerstattung
	* Rohstoff-Knappheit und -Überfluss
	* Produkte-Knappheit und -Überfluss
	* Pandemie
	* Spekulationsfreudig
	* Arbeiter gebunden Events
		* Schwangerschaft
		* Krankheit
		* Arbeiter kündigt sich selber
		* Stimmungschwankungen
* Spielstand
	* Sichern
	* Datei verschlüsseln
* ___Zustandsliste___
	* Für die Phasen, um die JSON klein zuhalten
	* JSON Serialisere und Deserialisierer
	* GsonBuilder zum schreiben und zum auslesen
		* Gson Strategie:
			* Alle Modifier mit Modifier.TRANSIENT werden exkludet
* Fachmethoden in der Sprache der Kunden halten
	* sonst für Entwickler schwierig nach zu folgen
* (gutes) GUI
	* HauptMenu
		* Highscore
		* Hilfe
	* Spielscreen
		* funktionierende Knöpfe
		* funktionierende Textfelder
		* Arbeiterscreen scrollbar
		* JScrollbar für jtextarea
		* Durchschnittliche Stimmung der Arbeiter einprogrammieren
* Logik
	* Alle 15 Phasen wird der Preis der Einkaufspreis erhöht

## Warum geht es?
### Spielkonzept OvGu:
* Start des Spiels:
	*  Rohstoffe: 2
	* Geld: 10
* Verarbeitbar 1 Rohstoff zu 4 Produkten
* 2 Phasen:
	* Einkaufsphase
		* Man kann Rohstoffe kaufen
		* mindestens 2 Stück, maximal x Stück, basierend auf das Vermögen
	* Verkaufsphase
		* alle Produkte sind verkaufbar
* Preise:
	* Entweder Random-Generator oder Funktionswertbasierend
* Verliert: wenn man nichts mehr machen kann, weder kaufen noch verkaufen → Pleite
* Kreditrahmen: [im Urkonzept nicht vorgesehen]

### Was will ich?
* Hauptmenü
* schöneres GUI --> altes GUI „wegschmeißen“
	* unten alle wichtigen Informationen
	* Arbeiter, Vermögen, Umsatz, etc.
	* entsprechende Knöpfe erscheinen erst in der richtigen Phase
	* ein Knopf für An- und Verkauf
* funktionstüchtiges Programm
* realistische Einkaufspreise/Verkaufspreise
* Rohstoff knappheit → sehr hohe Preise
* Rohstoff überfluss → sehr niedrige Preise
* analog für den Verkauf
	*  Mit info anzeigen: zB: „In Indien wurde {Rohstoff} gefunden...“
* Namen für Produkt und Rohstoff
* Steuern implementieren, zB: alle 10 Runden abgezogen
	* Umsatzsteuer
	* Einkommensteuer
	* Gewerbesteuer
	*  Arbeiter einstellen
		*  Kosten pro Runde, etc
		* Pro Arbeiter 4? Rohstoffe verarbeitbar
		* ___Gehalt der Arbeiter___
		* Motivation der Arbeiter
		* ___Erkennung, ob ein Spieler Pleite ist___
		* Spielstand sichern
			* ___wenn früher fertig als gedacht___

## benutze Bibliotheken
* Gson
	* Guide: https://sites.google.com/site/gson/gson-user-guide
	* Doc: https://www.javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/package-summary.html
* Base64
  * Download: http://commons.apache.org/proper/commons-codec/download_codec.cgi
  * Doc: https://docs.oracle.com/javase/8/docs/api/java/util/Base64.Encoder.html
* Log4J
* JDBC-Connector (MySQL)

Bitte herunterladen und im Projekt importieren. Aus Lizenz-technischen Bedenken, veröffentliche die nicht im Repository. Sowie die Bilder, die ich aus BDcraft "genommen" habe.
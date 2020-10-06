# Erklärungen

## Links

### Shortcuts
http://www.java-programmieren.com/eclipse-shortcuts.php

### log4j
https://www.tutorialspoint.com/log4j/log4j_logging_levels.htm

### JavaPapers
https://javapapers.com/

## Logging
* -> Sicherheitsrisiko, hacker kann es manipulieren.
* es gibt bibliotheken zum loggen, nachrichten für mich in eine datei schreiben.
* Debugmode modus, während des spiel läuft
* jeden Schritt in eine datei schreiben
* zB. 
	* Erzeuge {name} objekt
	* Methode xy wurde ausgeführt.
	    
### Log4J

#### Apennder: 
* wo kommen die Ströme hin
* namen frei wählbar

#### File Apender:
* schreibt Strom in Datei

#### RollingFileAppender:
* meist vorkommen
* schreibt in Datei
* maximale Größe der Datei angeben
* alte: umbenennen und packen
* maximale anzahl von logs

#### Asynchroner Appender:
* im eigenen Thread
* beeinflusst nicht die Laufzeit des Programms

#### Logger:
* was zu loggen
* name="package-angabe"
* Logger definiert hat
* level: w

## JSON
1. @Transient beudet, das irgendwas nicht abgespeichert werden soll. Sofern man mit GsonBuilder, diese MoDIFIERS ausblendet.
* Gson Strategie:
	* Alle Modifier mit Modifier.STATIC, Modifier.TRANSIENT werden exkludet

### Weiterführende Links zum Problem GSON
* Handbuch
	* https://sites.google.com/site/gson/gson-user-guide#TOC-Collections-Examples
* http://tutorials.jenkov.com/java-json/gson.html
* https://www.javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/package-summary.html
* https://stackoverflow.com/questions/5554217/google-gson-deserialize-listclass-object-generic-type?rq=1
* https://stackoverflow.com/questions/10209959/gson-tojson-throws-stackoverflowerror
* https://stackoverflow.com/questions/18567719/gson-deserializing-nested-objects-with-instancecreator

### Download GSON
* Download gson.jar
	* https://search.maven.org/artifact/com.google.code.gson/gson/2.8.6/jar

## Encryption in Java
* https://www.codeflow.site/de/article/java-cipher-input-output-stream
* KeyStore
	* KeyStore per Terminal auslesen: http://www.itrig.de/index.php?/archives/1944-9-praktische-Keytool-Befehle-Zertifikatsmanagement-unter-Java.html
	
## Datenbanken in Java
* MySQL in Java
	* https://www.vogella.com/tutorials/MySQLJava/article.html
* ORM Mapper -> machen Objekte aus der Datenbank
	* -> HyberNate -> Winterschlaf auf englisch
* oder per hand mit jdbc treiber
	* -> anfang: lernen besser Weg
* --> tabellenLayout als objektstruktur

	
## Jackson 2
* mal danach googeln
* gängige Lib, für JSON

## Nützliche Programme für Eclipse
* Checkstyle
* PMD
* SpotBugs
--> geben Hinweise für besseren Code

## Versionsverwaltung
Java-Versionenverwaltung -> Java lib um objekte zu speichern -> Interface
serializebel implementieren Beschreibt Stand des Quellcodes speichern =
serialisieren --> bei großen abändern, nummer/UID ändern

Web-Apps: App-Server: aktuellen zustand speichern + gucken Aktuelle seasion

## Java Garbage Collector
* Java benutzerverzechnis
* System.gc() -> zuwenig speicher eingestellt.
* wegen zyklische abhängigkeiten
	* b references a
	* a references b

* java:
	* mehrer gc implementierung
		* 1. pausiert eine ganze applikation
		* 2. pausiert nicht meine applikation

## .jar Signieren
### 1. Keystore generieren
* keytool -genkey -alias [name] -keystore [name].jks

### 2. Key exportieren & selbstsignieren
* keytool -export -alias duke -file selfsigned.crt
* keytool -printcert -file selfsigned.crt

### 3. Jar Signieren
* jarsigner testsim.jar urs

### 4. Jar Überprüfen
* jarsigner -verify [name].jar

## Besondere Java geschichten
### Helper Utils Klassen
* enden mit *Helper oder *Utils
* beinhalten nur static Methoden/Variablen
* besitzen einen privaten Konstruktor
* ___Helper___
	* unterstützen andere Klassen
	* sind "public final class" Klassen
* ___Utils___
	* Speichern Standard Werte
	* meist getter
	* --> ist "public final class {name}"
	
### Methoden, die Konstruktor aufgerufen werden
* müssen "final" sein
* damit sie nicht überschrieben werden dürfen

### Methoden, Variablen, die nicht bearbeitet werden dürfen (AUCH beim übergeben an Methoden)
* sofort final hin schreiben
* wenn man sie bearbeitet? - Frage:
	* darf man die bearbeiten, dass keine Seiteneffekte hervorkommen?
	* Warum muss man die bearbeitet können?
* sonst: final lassen

### Alle Methoden/Variblen mit MODIFER
#### private static final
* MÜSSEN IN GROẞBUCHSTABEN DEKLARIERT WERDEN

#### private, public oder protectet
* werden am Anfang klein geschrieben werden

### Methoden
* Bei try-catch Methoden, immer throws verwenden, damit man nicht immer ein abfang machen muss.

## List<>
* java.util.LIST für Arbeiter mappen 
* ArrayList: nicht thread sicher.
# Java Projekt-Template fuer OOP/PLAB, PRG, AD und SWDE

[![build status](https://intern-gitlab.enterpriselab.ch/oop/oop_maven_template/badges/develop/build.svg)](https://intern-gitlab.enterpriselab.ch/oop/oop_maven_template/commits/develop)

## Zweck
Dieses Projekt dient in den Modulen OOP/PLAB, PRG, AD, SWDE, VSK und APPE als Template fuer
einfache Java-Projekte. Es nutzt einen auf Apache Maven basierenden (und somit IDE-unabhaengigen)
Build und definiert die dafür notwendige, standardisierte Verzeichnisstruktur.

## Verwendung
Das Projekt kann einfach kopiert und umbenannt werden. In NetBeans kann es **direkt** geoeffnet werden,
in Eclipse und IntelliJ IDEA ist ein **Import** des Projektes notwendig.
**ACHTUNG**: Dieses Template benoetigt mindestens Java 11! Fuer aeltere Java Versionen (1.8) nutzen
Sie bitte ein Template auf der Basis von Version 1.5.x

Nach dem Kopieren in ein neues Verzeichnis empfehlen wir, den Namen
des Verzeichnisses, den Namen des Projektes in der IDE sowie die 
"ArtifactID" und "Name" (zwei Elemente im pom.xml) **synchron** zu halten.
In NetBeans erreichen Sie das durch ein einfaches "Rename"-Refactoring. 
Sie koennen es aber auch einfach "von Hand" erledigen.

Sobald Sie eigene Klassen und Testfaelle ergaenzt haben, sollten Sie die
fuer Demozwecke enthaltenen Demo-Klassen entfernen.

## Enhaltene Libraries (Dependencies)
* JUnit 5 - https://junit.org/junit5/
* JUnit Pioneer - https://junit-pioneer.org/
* AssertJ - https://assertj.github.io/doc/
* EqualsVerifier - https://jqno.nl/equalsverifier/
* Logging Framework - https://logging.apache.org/log4j/2.x/

## Integrierte Analysewerkzeuge
* Checkstyle - https://checkstyle.sourceforge.io/
* PMD - https://pmd.github.io/
* JaCoCo - https://www.eclemma.org/jacoco/
* Spotbugs - https://spotbugs.github.io/

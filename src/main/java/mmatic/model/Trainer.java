/**
 * 
 */
package mmatic.model;
import java.util.ArrayList;
import java.util.Random;

/**
 * Diese Klasse erstellt einen Objekt ArrayList
 * und bietet verschiedene Methoden den zu bearbeiten
 * @author Milos Matic
 * @version 27.09.2024
 */
public class Trainer {
    private ArrayList<Paar> liste = new ArrayList<>();
    private Paar sPaar; // selected Paar
    private int gesamtAnzahl = 0;
    private int richtigAnzahl = 0;
    private int falschAnzahl = 0;

    /**
     * Konstruktor fuer ganeze Erzeugung
     * @param paar
     */
    public Trainer(Paar paar) throws IllegalArgumentException{
        if(paar == null)
            throw new IllegalArgumentException("Liste darf nicht null sein!");
        this.liste.add(paar);
    }
    
    /**
     * Waehlt eine zufaellige Paar und speichert es im Attribut sPaar
     */
    public void select() {
        Random rn = new Random();
        sPaar = liste.get(rn.nextInt(liste.size()));
    }

    /**
     * W�hlt eine zufaellige Paar und speichert es im Attribut sPaar
     */
    public void select(int i) {
        sPaar = liste.get(i);
    }

    /**
     * Gibt dem in der obere Methode gewaehlte Paar zur�ck
     * @return sPaar der in obere Methode gewaehlte Paar
     */
    public Paar getBild() {
        return sPaar;
    }
    
    /**
     * Ueberprueft ob im Parameter uebergebene wort mit dem Wort des aktuell ausgewaehlten Paares uebereinstimmt
     * @param wort
     * @return true wenn die Woerter uebereinstimmen
     * @throws IllegalArgumentException wenn der Parameter ungueltig ist
     */
    public boolean guess(String wort) throws IllegalArgumentException{
        if(wort != null) {
            boolean richtig = sPaar.getWort().equals(wort) ? true : false;
            if (richtig) {
                select();
            }
            return statistic(richtig);
        }
        throw new IllegalArgumentException("Ungueltige Parameter");
    }

    /**
     * Macht das gleiche wie Methode guess nur ignoriert sie die Gross- und Kleinschreibung
     * @param wort
     * @return true wenn die Woerter uebereinstimmen
     * @throws IllegalArgumentException wenn der Parameter ungueltig ist
     */
    public boolean guessIgnoreCase(String wort) throws IllegalArgumentException {
        return guess(wort.toLowerCase());
    }

    /**
     * Diese Methode wird dafuer verwendet, um die Anzahl der abgefragte
     * und die Anzahl der richtige ueberpruefungen zu zaehlen
     * @param richtig true wenn richtig
     * @return richtig Parameter
     */
    private boolean statistic(boolean richtig) {
        gesamtAnzahl++;
        if(richtig) {
            this.richtigAnzahl++;
        }
        else{
            this.falschAnzahl++;
        }
        return richtig;
    }
    
    /**
     * Die Prozentanzahl der richtig abgefragten Worte zu berechnen und ausgeben
     * @return die Prozentanzahl
     */
    public double getStatistic() {
        return (double)richtigAnzahl / (double)gesamtAnzahl * 100;
    }

    public ArrayList getTrainerListe() {
        return liste;
    }
    public int getRichtig() {
    	return richtigAnzahl;
    }
    public int getAbgefragt() {
    	return gesamtAnzahl;
    }
    public void zuruecksetzen() {
    	richtigAnzahl = 0;
    	gesamtAnzahl = 0;
    }

    /**
     * Erzeugt ein neuses Paar mit der ueberegebenen Parameter und gibt diese mithilfe von addPaar Methode in der array
     * @param wort
     * @param url
     * @throws IllegalArgumentException wenn der Parameter ungueltig ist
     */
    public void addPaar(String wort, String url) throws IllegalArgumentException{
        liste.add(new Paar(wort, url));
    }

    public void addPaar(Paar paar) {
        liste.add(paar);
    }

    /**
     * Gibt einen Worteintrag am uebergebenem Platz zurueck
     * @param i uebergebene Platz
     * @return worteintrag am uebergebenem Platz
     */
    public Paar getPaar(int i) {
        return liste.get(i);
    }

    /**
     * Sucht den Worteintrag der das gleiche wort hat wie der uebergebene Parameter und loescht den
     * @param wort
     * @return true bei Erfolg
     */
    public void deletePaar(String wort) throws IllegalArgumentException{
        if(wort != null) {
            int anzahl = 0;
            for (int i = 0; i < liste.size(); i++) {
                if(liste.get(i).getWort().equals(wort)) {
                    liste.remove(i);
                }
            }
        }
        throw new IllegalArgumentException("Der Objekt String muss erzeugt werden.");
    }

    /**
     * Fasst alle WortEintraege in einem string zusammen
     * @return zusammengefasste text
     */
    @Override
    public String toString() {
        String text = "";
        for (int i = 0; i < liste.size()-1; i++) {
            text += liste.get(i).toString() + "\n";
        }
        text += liste.get(liste.size()-1).toString();
        return text;
    }
}


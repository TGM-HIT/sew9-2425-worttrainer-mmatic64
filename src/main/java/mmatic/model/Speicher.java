package mmatic.model;
import java.io.*;

/**
 * Diese Klasse hat nur die aufgabe den worttrainer zu speichern und laden.
 *
 * @author Milos Matic
 * @version 30.09.2024
 */
public class Speicher {
    private SpeicherStrategie strategie;

    public Speicher(SpeicherStrategie strategie) {
        this.strategie = strategie;
    }

    /**
     * Speichert einen Trainer in die uebergebene Datei
     *
     * @param filename, wo der Trainer gespeichert wird
     * @throws FileNotFoundException, wenn die Datei nicht gefunden ist
     * @throws IOException, wenn die Datei nicht gefunden ist
     * @throws IllegalArgumentException, wenn einer der Parameter null ist
     */
    public void speichern(Trainer trainer, String filename) throws IOException, IllegalArgumentException {
        strategie.speichern(trainer, filename);
    }

    /**
     * Speichert einen Trainer in der Default-Datei "Worttrainer.txt"
     *
     * @throws FileNotFoundException, wenn die Datei nicht gefunden ist
     * @throws IOException, wenn die Datei nicht gefunden ist
     * @throws IllegalArgumentException, wenn einer der Parameter null ist
     */
    public void speichern(Trainer trainer) throws FileNotFoundException, IOException, IllegalArgumentException{
        speichern(trainer, "resource/worttrainer.txt");
    }

    /**
     * Ladet einen Trainer aus der gegebenen Datei
     *
     * @return Trainer, der geladene Trainer Objekt
     * @throws FileNotFoundException, wenn die Datei nicht gefunden ist
     * @throws IOException, wenn die Datei nicht gefunden ist
     */
    public Trainer laden(String filename) throws FileNotFoundException, IOException {
       return strategie.laden(filename);
    }

    /**
     * Ladet einen Trainer aus der Default-Datei "Worttrainer.txt"
     *
     * @return Trainer, der geladene Trainer Objekt
     * @throws FileNotFoundException, wenn die Datei nicht gefunden ist
     * @throws IOException, wenn die Datei nicht gefunden ist
     */
    public Trainer laden() throws FileNotFoundException, IOException {
        return laden("resource/worttrainer.txt");
    }
}

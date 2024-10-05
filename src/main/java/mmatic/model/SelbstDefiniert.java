package mmatic.model;

import java.io.*;

/**
 * Diese Klasse ist eine Strategie zum speichern und laden von Trainer Objekten
 * dabei wird eine selbstdefinierte methode verwendet
 *
 * @author Milos Matic
 * @version 04.10.2024
 */
public class SelbstDefiniert implements SpeicherStrategie {

    @Override
    public void speichern(Trainer trainer, String filename) throws IOException, IllegalArgumentException {
        if(trainer == null) {
            throw new IllegalArgumentException("Worttrainer darf nicht null sein!");
        }
        File file = new File(filename);
        try (FileWriter outputStream = new FileWriter(file)) {
            outputStream.write(trainer.toString());
        }
    }

    @Override
    public Trainer laden(String filename) throws FileNotFoundException, IOException {
        try (BufferedReader inputStream = new BufferedReader(new FileReader(filename))) {
            Trainer trainer = new Trainer();
            int gesamtAnzahl, richtigAnzahl, falschAnzahl;
            String zeile, wort, url;
            zeile = inputStream.readLine();
            if (zeile == null)
                throw new FileNotFoundException("Datei ist leer!");
            String[] zahlen = zeile.split(";");
            gesamtAnzahl = Integer.parseInt(zahlen[0]);
            richtigAnzahl = Integer.parseInt(zahlen[1]);
            trainer.setStatistic(gesamtAnzahl, richtigAnzahl);
            while (((zeile = inputStream.readLine())) != null) {
                int i = zeile.indexOf(';');

                if(i == -1)
                    throw new FileNotFoundException("Dateiformat ist nicht richtig!");

                wort = zeile.substring(0, i);
                url = zeile.substring(i+2);

                trainer.addPaar(new Paar(wort, url));
            }
            return trainer;
        }
    }

}

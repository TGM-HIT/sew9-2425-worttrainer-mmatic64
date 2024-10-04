package mmatic.model;

import java.io.*;
import java.util.ArrayList;

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
            String zeile, wort, url;
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

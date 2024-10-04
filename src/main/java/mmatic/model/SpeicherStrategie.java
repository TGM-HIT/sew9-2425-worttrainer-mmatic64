package mmatic.model;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface SpeicherStrategie {
    public void speichern(Trainer trainer, String filename) throws IOException, IllegalArgumentException;
    public Trainer laden(String filename) throws FileNotFoundException, IOException;
}

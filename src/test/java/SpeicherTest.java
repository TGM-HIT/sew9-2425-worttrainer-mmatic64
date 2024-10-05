import mmatic.model.Paar;
import mmatic.model.SelbstDefiniert;
import mmatic.model.Speicher;
import mmatic.model.Trainer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.*;

public class SpeicherTest {
    Trainer trainer;
    @BeforeEach
    public void setUp() {
        trainer = new Trainer();
        trainer.addPaar(new Paar("Wort1", "https://www.google1.com"));
        trainer.addPaar(new Paar("Wort2", "https://www.google2.com"));
        trainer.select();
    }

    @Test
    void speichern() {
        Speicher speicher = new Speicher(new SelbstDefiniert());
        try {
            speicher.speichern(trainer);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Trainer trainer2 = null;
        try {
            trainer2 = speicher.laden();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        assertEquals(trainer.getPaar(0).getWort(), trainer2.getPaar(0).getWort());
        assertEquals(trainer.getPaar(0).getURL(), trainer2.getPaar(0).getURL());
        assertEquals(trainer.getPaar(1).getWort(), trainer2.getPaar(1).getWort());
        assertEquals(trainer.getPaar(1).getURL(), trainer2.getPaar(1).getURL());
        assertEquals(trainer.getStatistic(), trainer2.getStatistic());
    }
}

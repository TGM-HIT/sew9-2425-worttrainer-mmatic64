import mmatic.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    @BeforeEach
    public void setUp() {
    }

    @Test
    void setPaar() {
        Paar paar = new Paar("Wort", "https://www.google.com");
        assertEquals("Wort", paar.getWort());
        assertEquals("https://www.google.com", paar.getURL());
        assertThrows(new Paar("wort", "htt://www.google.com"))
    }

    @Test
    void setTrainer() {
        Paar paar = new Paar("Wort", "https://www.google.com");
        Trainer trainer = new Trainer(paar);
        trainer.select();
        assertEquals(paar, trainer.getBild());
    }

    @Test
    void guess() {
        Paar paar1 = new Paar("Wort1", "https://www.google1.com");
        Paar paar2 = new Paar("Wort2", "https://www.google2.com");
        Paar paar3 = new Paar("Wort3", "https://www.google3.com");
        Trainer trainer = new Trainer(paar1);
        trainer.addPaar(paar2);
        trainer.addPaar(paar3);
        trainer.select(1);
        assertFalse(trainer.guess("false"));
        assertTrue(trainer.guess("Wort2"));
        trainer.select(2);
        assertFalse(trainer.guess("Wort2"));
        assertTrue(trainer.guess("Wort3"));
        trainer.select(0);
        assertFalse(trainer.guess("false"));
        assertEquals(40.0, trainer.getStatistic());
    }
}

package no.hvl.terningspillet.Tests;

import no.hvl.terningspillet.spillet.Spiller;
import no.hvl.terningspillet.spillet.Terningspillet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TerningspilletTests {
    private Terningspillet spillet;

    @BeforeEach
    public void setup(){
        spillet = new Terningspillet(1, 3);
        for (int i = 0; i < 3; i++){
            spillet.leggTilSpiller();
        }
        for (int i = 0; i < 3; i++){
            String navn = "Spiller " + i;
            spillet.getSpillere().get(i).setNavn(navn);
        }
    }

    @Test
    public void testUavgjort(){
        for (Spiller s : spillet.getSpillere()){
            s.setVerdi(10);
        }
        assertTrue(spillet.isUavgjort());
        assertEquals(3, spillet.hentVinnere().size());
        // Gir en spiller ett mer poeng enn de andre
        spillet.getSpillere().get(0).setVerdi(11);
        assertFalse(spillet.isUavgjort());
    }

    @Test
    public void testSort(){
        int points = 1;
        for (Spiller s : spillet.getSpillere()){
            s.setVerdi(points);
            points++;
        }
        List<Spiller> sortert = spillet.sort(spillet.getSpillere());
        assertEquals(3, sortert.get(0).getVerdi());
    }
}

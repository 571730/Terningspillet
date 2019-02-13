package no.hvl.terningspillet.Tests;

import no.hvl.terningspillet.spillet.Spiller;
import no.hvl.terningspillet.spillet.Terningspillet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TerningspilletTests {
    private Terningspillet spillet;

    @BeforeEach
    public void setup(){
        spillet = new Terningspillet(1, 3);
    }

    @Test
    public void testUavgjort(){
        for (Spiller s : spillet.getSpillere()){
            s.setVerdi(10);
        }
        assertTrue(spillet.isUavgjort());
        assertEquals(3, spillet.hentVinnere().size());
    }
}

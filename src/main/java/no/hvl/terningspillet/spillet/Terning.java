package no.hvl.terningspillet.spillet;

import java.util.Random;

/**
 * Terningen, kan faa verdi fra 1 til 6
 */
public class Terning {
    private int id;
    private int verdi;
    private Random rnd;

    /**
     * Oppretter terning med Random objekt som brukes til aa trille terning
     * @param id
     */
    public Terning(int id){
        this.id = id;
        rnd = new Random();
    }

    /**
     * Triller terningen, faar verdi fra 1 til 6
     */
    public void trill(){
        this.verdi = rnd.nextInt(6) + 1;
    }

    public int getVerdi() {
        return verdi;
    }

    public void setVerdi(int verdi) {
        this.verdi = verdi;
    }
}

package no.hvl.terningspillet.spillet;

import java.util.Random;

public class Terning {
    private int id;
    private int verdi;
    private Random rnd;

    public Terning(int id){
        this.id = id;
        rnd = new Random();
    }

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

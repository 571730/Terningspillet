package no.hvl.terningspillet.spillet;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;


public class Terningspillet {
    private int id;
    private Kopp kopp;
    private ArrayList<Spiller> spillere;
    private int antallSpillere;
    private boolean startet;
    private boolean ferdig;
    private int tur;

    public Terningspillet(int id, int antallSpillere){
        this.id = id;
        kopp = new Kopp(id);
        spillere = new ArrayList<>();
        this.antallSpillere = antallSpillere;
        startet = false;
        ferdig = false;
    }

//    public void leggTilSpillere(){
//        for (int i = 0; i < antallSpillere; i++){
//            leggTilSpiller();
//        }
//    }

    public boolean isStartet() {
        return startet;
    }

    public void leggTilSpiller(){
        spillere.add(new Spiller());
    }

    public void spill(){
        startet = true;
//        for (Spiller spiller : spillere){
//            spiller.spill(kopp);
//        }
        tur = 0;
    }

    public void spillTur(){
        spillere.get(tur).spill(kopp);
        tur++;
        if (erFerdig()){
            ferdig = true;
        }
    }

    public void reset(){
        for (Spiller spiller : spillere){
            spiller.setVerdi(0);
        }
        tur = 0;
        startet = true;
        ferdig = false;
    }

    public boolean isFerdig() {
        return ferdig;
    }

    public boolean erFerdig(){
        return tur == spillere.size();
    }

    public Spiller getVinner(){
        Spiller vinner = spillere.stream().max(Comparator.comparing(Spiller::getVerdi)).orElseThrow(NoSuchElementException::new);
        return vinner;
    }

    public Kopp getKopp() {
        return kopp;
    }

    public void setSpillere(ArrayList<Spiller> spillere) {
        this.spillere = spillere;
    }

    public ArrayList<Spiller> getSpillere() {
        return spillere;
    }

    public int getAntallSpillere() {
        return antallSpillere;
    }
}

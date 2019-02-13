package no.hvl.terningspillet.spillet;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


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

    public boolean isStartet() {
        return startet;
    }

    public void leggTilSpiller(){
        spillere.add(new Spiller());
    }

    public void spill(){
        startet = true;
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
        for (Terning t : kopp.getTerninger()){
            t.setVerdi(0);
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
        return spillere.stream().max(Comparator.comparing(Spiller::getVerdi)).orElseThrow(NoSuchElementException::new);
    }

    public boolean isUavgjort(){
        List<Spiller> sortert = sort(spillere);
        return (sortert.get(0).getVerdi() == sortert.get(1).getVerdi());
    }

    public List<Spiller> sort(ArrayList<Spiller> listen){
        return listen.stream()
                .sorted(Comparator.comparing(Spiller::getVerdi).reversed())
                .collect(Collectors.toList());
    }

    public ArrayList<Spiller> hentVinnere(){
        ArrayList<Spiller> vinnere = new ArrayList<>();
        List<Spiller> sortert = sort(spillere);
        int vinnerSum = sortert.get(0).getVerdi();
        for (Spiller s : sortert){
            if (s.getVerdi() == vinnerSum)
                vinnere.add(s);
        }
        return vinnere;
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

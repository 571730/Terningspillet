package no.hvl.terningspillet.spillet;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Terningspillet er et spill hvor en kaster to terninger og summerer de to.
 * Den som faar hoyest sum er vinneren.
 * Dette prosjektet er tilpasset aa bli en web applikasjon, dermed er
 * en del kanskje litt rare losninger tatt for aa sikre bedre opplevelse som en nettside.
 */
public class Terningspillet {
    private int id;
    private Kopp kopp;
    private ArrayList<Spiller> spillere;
    private int antallSpillere;
    private boolean startet;
    private boolean ferdig;
    private int tur;

    /**
     * Setter opp spillet, som videre lager kopp og terninger. Lager ikke spillere med en gang,
     * grunnet tilretteleggelse for web app.
     * @param id for spill som brukes videre paa kopp og slikt
     * @param antallSpillere som skal spille
     */
    public Terningspillet(int id, int antallSpillere){
        this.id = id;
        kopp = new Kopp(id);
        spillere = new ArrayList<>();
        this.antallSpillere = antallSpillere;
        startet = false;
        ferdig = false;
    }

    /**
     * State som passer holder orden paa om spillet er startet eller ikke
     * @return boolean om spillet er startet
     */
    public boolean isStartet() {
        return startet;
    }

    /**
     * Legger til en ny spiller i spillerlisten, uten navn, dette settes paa siden
     */
    public void leggTilSpiller(){
        spillere.add(new Spiller());
    }

    /**
     * Startet spillet
     * Tur holder orden paa hvilken spiller i listen sit tur det er.
     */
    public void spill(){
        startet = true;
        tur = 0;
    }

    /**
     * Bruker tur variabelen til aa spille riktig spiller sin tur
     * oker tur variabelen, og sjekker om spillet er ferdig.
     */
    public void spillTur(){
        spillere.get(tur).spill(kopp);
        tur++;
        if (erFerdig()){
            ferdig = true;
        }
    }

    /**
     * Legger opp spillet paa nytt.
     * Alle spillere faar 0 poeng igjen, terninger blir satt til 0.
     * Variabler som holder orden paa states settes tilbake.
     */
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

    /**
     * Sjekker om spillet er satt til ferdig
     * @return boolean om spillet er ferdig
     */
    public boolean isFerdig() {
        return ferdig;
    }

    /**
     * Sjekker om spillet er ferdig ved aa se paa om tur peker over siste
     * plass i listen over spillere
     * @return boolean om spillet er ferdig
     */
    public boolean erFerdig(){
        return tur == spillere.size();
    }

    /**
     * Finner den spilleren i listen som har hoyest poengsum
     * @return Spiller som er vinner
     */
    public Spiller getVinner(){
        return spillere.stream().max(Comparator.comparing(Spiller::getVerdi)).orElseThrow(NoSuchElementException::new);
    }

    /**
     * Sjekker om det blir uavgjort ved aa sorter aa sjekke om de to foerste
     * plassene er like.
     * @return boolean om det er uavgjort
     */
    public boolean isUavgjort(){
        List<Spiller> sortert = sort(spillere);
        return (sortert.get(0).getVerdi() == sortert.get(1).getVerdi());
    }

    /**
     * Tar i mot en liste med spillere og sorterer den
     * @param listen den som skal sorteres
     * @return  sortert liste
     */
    public List<Spiller> sort(ArrayList<Spiller> listen){
        return listen.stream()
                .sorted(Comparator.comparing(Spiller::getVerdi).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Om det er uavgjort bruker du denne metoden for aa hente ut alle
     * som er vinnere
     * @return liste over alle vinnere
     */
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

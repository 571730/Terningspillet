package no.hvl.terningspillet.spillet;

/**
 * Denne klassen representerer en spiller
 * Den har navn og verdi
 * verdi er antall poeng spilleren har
 */
public class Spiller {
    private String navn;
    private Integer verdi;

    /**
     * Oppretter spiller og setter poeng til 0
     */
    public Spiller(){
        verdi = 0;
    }

    /**
     * Oppretter spiller med navn og setter poeng til 0
     * @param navn
     */
    public Spiller(String navn){
        this.navn = navn;
        verdi = 0;
    }

    /**
     * Spilleren triller koppen, faar tilbake summen av terningene.
     * Setter summen paa verdi
     * @param kopp med terninger
     */
    public void spill(Kopp kopp){
        verdi = kopp.trill();
    }

    public void setVerdi(Integer verdi) {
        this.verdi = verdi;
    }

    public int getVerdi() {
        return verdi;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
}

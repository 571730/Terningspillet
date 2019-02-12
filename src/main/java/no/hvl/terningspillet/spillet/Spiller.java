package no.hvl.terningspillet.spillet;

public class Spiller {
    private String navn;
    private Integer verdi;

    public Spiller(){
        verdi = 0;
    }
    public Spiller(String navn){
        this.navn = navn;
        verdi = 0;
    }
    public void spill(Kopp kopp){
        verdi = kopp.trill();
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

package no.hvl.terningspillet.spillet;

public class Kopp {
    private int id;
    private int sum;
    private Terning[] terninger;

    public Kopp(int id){
        this.id = id;
        sum = 0;
        terninger = new Terning[2];
        terninger[0] = new Terning(id);
        terninger[1] = new Terning(id);
    }

    public Terning[] getTerninger() {
        return terninger;
    }

    public int trill(){
        sum = 0;
        for (int i = 0; i < terninger.length; i++){
            terninger[i].trill();
            sum += terninger[i].getVerdi();
        }
        return sum;
    }
}

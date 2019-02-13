package no.hvl.terningspillet.spillet;

/**
 * Koppen holder kontroll paa alle terninger
 */
public class Kopp {
    private int id;
    private int sum;
    private Terning[] terninger;

    /**
     * Oppretter en kopp, og lager terninger til koppen
     * @param id
     */
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

    /**
     * Triller alle terninger i koppen
     * @return summen av alle terninger i koppen
     */
    public int trill(){
        sum = 0;
        for (int i = 0; i < terninger.length; i++){
            terninger[i].trill();
            sum += terninger[i].getVerdi();
        }
        return sum;
    }
}

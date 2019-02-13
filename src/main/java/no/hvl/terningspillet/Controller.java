package no.hvl.terningspillet;


import no.hvl.terningspillet.spillet.Spiller;
import no.hvl.terningspillet.spillet.Terningspillet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * Kontrolleren som fikser routing til forskjellig metoder og slikt
 */
@org.springframework.stereotype.Controller
public class Controller {

    Terningspillet spillet;

    /**
     * Forsiden
     * @return navn paa html-fil som viser forsiden
     */
    @GetMapping("/")
    public String forside(){
        return "index";
    }

    /**
     * Tar i mot POST request som sier noe om hvor mange spillere som skal spille
     * @param stringAntall antall spillere i streng
     * @param model brukes til aa lagre data
     * @return redirect til leggTil siden
     */
    @PostMapping("/antall")
    public String antallSpillere(@RequestParam("antallSpillere") String stringAntall, Model model){
        int antall = Integer.parseInt(stringAntall);
        spillet = new Terningspillet(1, antall);
        for (int i = 0; i < antall; i++){
            spillet.leggTilSpiller();
        }
        return "redirect:/leggTil";
    }

    /**
     * POST som tar i mot navn paa alle spillere
     * @param spillere liste med navn over spillere
     * @param model
     * @return redirect til spillsiden
     */
    @PostMapping("leggTilSpillere")
    public String postLeggTilSpillere(@RequestParam("name") String[] spillere, Model model){
        ArrayList<Spiller> spillerListe = new ArrayList<>();
        for (int i = 0; i < spillere.length; i++){
            spillerListe.add(new Spiller(spillere[i]));
        }
        spillet.setSpillere(spillerListe);
        model.addAttribute("spillerListe", spillet.getSpillere());
        return "redirect:/spill";
    }

    /**
     * Spillsiden, det er her vi spiller
     * @param model brukes til aa lagre data som skal brukes paa siden
     * @return navn paa html
     */
    @GetMapping("/spill")
    public String spill(Model model){
        model.addAttribute("vinner", spillet.getSpillere().get(0));
        if (spillet.isFerdig()){
            if (spillet.isUavgjort()){
                model.addAttribute("uavgjort", spillet.hentVinnere());
            } else {
                model.addAttribute("vinner", spillet.getVinner());
            }
        }
        model.addAttribute("spillerListe", spillet.getSpillere());
        model.addAttribute("startet", spillet.isStartet());
        model.addAttribute("ferdig", spillet.isFerdig());
        model.addAttribute("terninger", spillet.getKopp().getTerninger());
        return "spill";
    }

    /**
     * Resetter spillet
     * @return redirect til spillsiden
     */
    @GetMapping("/spill/reset")
    public String reset(){
        spillet.reset();
        return "redirect:/spill";
    }

    /**
     * Starter spillet
     * @return redirect til spillside
     */
    @GetMapping("/spill/go")
    public String runSpill(){
        spillet.spill();
        return "redirect:/spill";
    }

    /**
     * Spiller en runde av spillet
     * @return redirect til spillsiden
     */
    @GetMapping("/spill/runde")
    public String spillRunde(){
        spillet.spillTur();
        return "redirect:/spill";
    }

    /**
     * Siden som lar deg legge til navn paa alle spillere
     * @param model data til siden
     * @return routing til POST metoden som tar i mot data
     */
    @GetMapping("/leggTil")
    public String leggTilSpillere(Model model){
        model.addAttribute("spillere", spillet.getSpillere());
        return "leggTilSpillere";
    }

}

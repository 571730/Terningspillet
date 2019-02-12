package no.hvl.terningspillet;


import no.hvl.terningspillet.spillet.Spiller;
import no.hvl.terningspillet.spillet.Terningspillet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@org.springframework.stereotype.Controller
public class Controller {

    Terningspillet spillet;

    @GetMapping("/")
    public String forside(){
        return "index";
    }

    @PostMapping("/antall")
    public String antallSpillere(@RequestParam("antallSpillere") String stringAntall, Model model){
        int antall = Integer.parseInt(stringAntall);
        spillet = new Terningspillet(1, antall);
        for (int i = 0; i < antall; i++){
            spillet.leggTilSpiller();
        }
        return "redirect:/leggTil";
    }

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

    @GetMapping("/spill")
    public String spill(Model model){
        model.addAttribute("spillerListe", spillet.getSpillere());
        model.addAttribute("startet", spillet.isStartet());
        model.addAttribute("terninger", spillet.getKopp().getTerninger());
        return "spill";
    }

    @GetMapping("/spill/go")
    public String runSpill(){
        spillet.spill();
        return "redirect:/spill";
    }

    @GetMapping("/spill/runde")
    public String spillRunde(){
        spillet.spillTur();
    }

    @GetMapping("/leggTil")
    public String leggTilSpillere(Model model){
        model.addAttribute("spillere", spillet.getSpillere());
        return "leggTilSpillere";
    }

}

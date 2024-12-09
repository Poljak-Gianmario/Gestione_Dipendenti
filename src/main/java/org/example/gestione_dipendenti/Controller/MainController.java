package org.example.gestione_dipendenti.Controller;

import org.example.gestione_dipendenti.Model.Dipendente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    private final DipendenteController dipendenteController;
    public MainController(DipendenteController dipendenteController){
        this.dipendenteController = dipendenteController;
    }
    @RequestMapping("/home")
    public String home(Model model){
        List<Dipendente> dipendenti = dipendenteController.prendiTutti();
        model.addAttribute("dipendenti", dipendenti);

        return "home";
    }


}

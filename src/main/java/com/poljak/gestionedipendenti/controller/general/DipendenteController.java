package com.poljak.gestionedipendenti.controller.general;

import com.poljak.gestionedipendenti.model.Dipendente;
import com.poljak.gestionedipendenti.service.DipendenteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("dipendenti")
public class DipendenteController {

    private final DipendenteService dipendenteService;
    public DipendenteController(DipendenteService dipendenteService){
        this.dipendenteService = dipendenteService;
    }

    @PostMapping
    public boolean aggiungiDipendente(Dipendente dipendente, String email){
        return dipendenteService.aggiungiDipendente(dipendente,email);
    }

    @GetMapping
    public List<Dipendente> prendiTutti(String email){
        return dipendenteService.getDipendenti(email);
    }

    @DeleteMapping
    public boolean eliminaDipendente(@RequestParam int n_badge, @RequestParam String email){
        return dipendenteService.eliminaDipendente(n_badge,email);
    }

}

package com.poljak.gestionedipendenti.controller.api;

import com.poljak.gestionedipendenti.repository.DipendenteRepository;
import org.springframework.web.bind.annotation.*;
import com.poljak.gestionedipendenti.model.Dipendente;

import java.util.List;

@RestController
public class DipendenteController {

    private final DipendenteRepository dipendenteRepository;

    public DipendenteController (DipendenteRepository dipendenteRepository){
        this.dipendenteRepository = dipendenteRepository;
    }

    @GetMapping("/dipendente")
    public List<Dipendente> prendiTutti(){
        return this.dipendenteRepository.Tutti();
    }

    @PostMapping("/dipendente")
    public void aggiungiDipendente(@RequestBody Dipendente dipendente){this.dipendenteRepository.InserisciDipendente(dipendente);
    }

    @DeleteMapping("/dipendente/{n_badge}")
    public boolean eliminaDipendente(@PathVariable("n_badge") int numero_badge){return this.dipendenteRepository.EliminaDipendente(numero_badge);}


}

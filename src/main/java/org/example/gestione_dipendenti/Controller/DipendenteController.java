package org.example.gestione_dipendenti.Controller;

import org.example.gestione_dipendenti.Model.Repository.DipendenteRepository;
import org.springframework.web.bind.annotation.*;
import org.example.gestione_dipendenti.Model.Dipendente;

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
    public void aggiungiDipendente(@RequestBody Dipendente dipendente){
        this.dipendenteRepository.InserisciDipendente(dipendente);
    }

    @DeleteMapping("/dipendente/{n_badge}")
    public boolean eliminaDipendente(@PathVariable("n_badge") int numero_badge){
        return this.dipendenteRepository.EliminaDipendente(numero_badge);
    }


}

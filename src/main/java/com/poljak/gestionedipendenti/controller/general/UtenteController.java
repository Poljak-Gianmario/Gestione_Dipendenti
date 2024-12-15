package com.poljak.gestionedipendenti.controller.general;

import com.poljak.gestionedipendenti.repository.UtenteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtenteController {

    private final UtenteRepository utenteRepository;

    public UtenteController(UtenteRepository utenteRepository){
        this.utenteRepository = utenteRepository;
    }

    @GetMapping("/utente/azienda")
    public String getAzienda(String email){
        return utenteRepository.getNome(email);
    }
}

package com.poljak.gestionedipendenti.controller.general;

import com.poljak.gestionedipendenti.model.LoginProcessor;
import com.poljak.gestionedipendenti.repository.SubscribeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubscribeController {

    private final SubscribeRepository subscribeRepository;
    private final LoginProcessor loginProcessor;
    public SubscribeController(SubscribeRepository subscribeRepository, LoginProcessor loginProcessor){
        this.subscribeRepository = subscribeRepository;
        this.loginProcessor = loginProcessor;
    }



    @GetMapping("/subscribe")
    public String subscribe(){
        return "subscribe";
    }

    @PostMapping("/subscribe")
    public String subscribed(@RequestParam String email, @RequestParam String nome, @RequestParam String cognome,@RequestParam String azienda, @RequestParam String password){

        subscribeRepository.aggiungiUtente(nome,cognome,email,password,azienda);
        loginProcessor.setEmail(email);
        loginProcessor.setPassword(password);
        loginProcessor.login();

        return "redirect:/main";
    }

}

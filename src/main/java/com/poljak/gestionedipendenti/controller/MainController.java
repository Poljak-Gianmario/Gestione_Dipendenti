package com.poljak.gestionedipendenti.controller;

import com.poljak.gestionedipendenti.controller.api.DipendenteController;
import com.poljak.gestionedipendenti.controller.general.UtenteController;
import com.poljak.gestionedipendenti.service.login.LoggedUserManagementService;
import com.poljak.gestionedipendenti.model.Dipendente;
import com.poljak.gestionedipendenti.service.LoginCountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final DipendenteController dipendenteController;

    private final UtenteController utenteController;
    private final LoginCountService loginCountService;

    public MainController(LoggedUserManagementService loggedUserManagementService, DipendenteController dipendenteController, LoginCountService loginCountService, UtenteController utenteController){
        this.loggedUserManagementService = loggedUserManagementService;
        this.dipendenteController = dipendenteController;
        this.loginCountService = loginCountService;
        this.utenteController = utenteController;
    }

    @GetMapping("/main")
    public String principale(Model model){

        String username = loggedUserManagementService.getUsername();

        int count = loginCountService.getCount();
        if(username == null){
            return "redirect:/";
        } else {

            List<Dipendente> dipendenti = dipendenteController.prendiTutti();
            model.addAttribute("dipendenti", dipendenti);
            model.addAttribute("count", count);
            model.addAttribute("nome", utenteController.getAzienda(loggedUserManagementService.getUsername()));

            return "home";
        }
    }

    /*@RequestMapping("/login")
    public String login(){
        return "login";
    }*/

    @RequestMapping("/logout")
    public String logout(){
        loggedUserManagementService.setUsername(null);
        return "redirect:/";
    }








}

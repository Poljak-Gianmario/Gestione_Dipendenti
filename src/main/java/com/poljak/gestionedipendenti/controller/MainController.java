package com.poljak.gestionedipendenti.controller;

import com.poljak.gestionedipendenti.controller.general.DipendenteController;
import com.poljak.gestionedipendenti.controller.general.UtenteController;
import com.poljak.gestionedipendenti.service.login.LoggedUserManagementService;
import com.poljak.gestionedipendenti.model.Dipendente;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final DipendenteController dipendenteController;

    private final UtenteController utenteController;

    public MainController(LoggedUserManagementService loggedUserManagementService, DipendenteController dipendenteController, UtenteController utenteController){
        this.loggedUserManagementService = loggedUserManagementService;
        this.dipendenteController = dipendenteController;
        this.utenteController = utenteController;
    }

    @GetMapping("/main")
    public String principale(Model model){

        String username = loggedUserManagementService.getUsername();

        if(username == null){
            return "redirect:/";
        } else {

            List<Dipendente> dipendenti = dipendenteController.prendiTutti(username);
            model.addAttribute("dipendente", new Dipendente());
            model.addAttribute("dipendenti", dipendenti);
            model.addAttribute("nome", utenteController.getAzienda(loggedUserManagementService.getUsername()));

            return "home";
        }
    }

    @PostMapping("/main")
    public String aggiungiDipendente(@Valid @ModelAttribute Dipendente dipendente,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttrs) {

        if(bindingResult.hasErrors()){

            redirectAttrs.addFlashAttribute("message", "Aggiunta fallita");
            return "redirect:/main";

        }

        if(dipendenteController.aggiungiDipendente(dipendente,loggedUserManagementService.getUsername())) {
            redirectAttrs.addFlashAttribute("message", "Dipendente aggiunto con successo!");
        } else {
            redirectAttrs.addFlashAttribute("message", "Aggiunta fallita");
        }

            return "redirect:/main";

    }

    @DeleteMapping("/main/delete")
    public String eliminaDipendente(@RequestParam String n_badge,RedirectAttributes redirectAttrs){

        if (n_badge == null || n_badge.isEmpty()) {
            redirectAttrs.addFlashAttribute("message", "Inserisci un badge!");
            return "redirect:/main";
        }

        try {
            int badge = Integer.parseInt(n_badge);

            if (dipendenteController.eliminaDipendente(badge, loggedUserManagementService.getUsername())) {
                redirectAttrs.addFlashAttribute("message", "Dipendente eliminato con successo!");
            } else {
                redirectAttrs.addFlashAttribute("message", "Numero badge non trovato");
            }
        } catch (NumberFormatException e) {
            redirectAttrs.addFlashAttribute("message", "Il badge deve essere un numero valido");
        }

        return "redirect:/main";

    }


    @RequestMapping("/main/logout")
    public String logout(){
        loggedUserManagementService.setUsername(null);
        return "redirect:/";
    }


    @GetMapping("main/sessione")
    public String getEmailSessione(){
        return loggedUserManagementService.getUsername();
    }








}

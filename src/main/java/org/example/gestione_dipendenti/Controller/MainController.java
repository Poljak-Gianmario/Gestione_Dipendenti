package org.example.gestione_dipendenti.Controller;

import org.example.gestione_dipendenti.Login.LoggedUserManagementService;
import org.example.gestione_dipendenti.Model.Dipendente;
import org.example.gestione_dipendenti.Service.LoginCountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final DipendenteController dipendenteController;
    private final LoginCountService loginCountService;

    public MainController(LoggedUserManagementService loggedUserManagementService, DipendenteController dipendenteController, LoginCountService loginCountService){
        this.loggedUserManagementService = loggedUserManagementService;
        this.dipendenteController = dipendenteController;
        this.loginCountService = loginCountService;
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

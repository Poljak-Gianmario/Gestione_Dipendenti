package org.example.gestione_dipendenti.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String loginGet(){
        return "login";
    }

    @PostMapping("/")
    public String loginPost(@RequestParam String username, @RequestParam String email, Model model){

        boolean loggedIn = false;

        if(loggedIn){
            model.addAttribute("message", "Sei loggato!");
        } else {
            model.addAttribute("message", "Password errata!");
        }

        return "login";
    }
}

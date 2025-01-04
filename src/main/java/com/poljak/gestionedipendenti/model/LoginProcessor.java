package com.poljak.gestionedipendenti.model;

import com.poljak.gestionedipendenti.service.login.LoggedUserManagementService;
import com.poljak.gestionedipendenti.repository.LoginRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;


@Component
@RequestScope
public class LoginProcessor {


    private String email;
    private String password;

    private final LoggedUserManagementService loggedUserManagementService;
    private final LoginRepository loginRepository;

    public LoginProcessor(LoggedUserManagementService loggedUserManagementService, LoginRepository loginRepository) {
        this.loggedUserManagementService = loggedUserManagementService;
        this.loginRepository = loginRepository;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public boolean login() {

        String username = this.getEmail();
        String password = this.getPassword();

        boolean loginResult = false;


        if (loginRepository.matchCredenziali(email,password)) {
            loginResult = true;
            loggedUserManagementService.setUsername(username);
        }

        return loginResult;
    }

}

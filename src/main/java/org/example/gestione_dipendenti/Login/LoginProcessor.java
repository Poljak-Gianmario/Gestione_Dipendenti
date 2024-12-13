package org.example.gestione_dipendenti.Login;

import org.example.gestione_dipendenti.Model.Repository.LoginRepository;
import org.example.gestione_dipendenti.Service.LoginCountService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import static org.example.gestione_dipendenti.Security.PasswordHash.hashPassword;



@Component
@RequestScope
public class LoginProcessor {


    private String email;
    private String password;

    private final LoggedUserManagementService loggedUserManagementService;
    private final LoginCountService loginCountService;
    private final LoginRepository loginRepository;

    public LoginProcessor(LoggedUserManagementService loggedUserManagementService, LoginCountService loginCountService, LoginRepository loginRepository) {
        this.loggedUserManagementService = loggedUserManagementService;
        this.loginCountService = loginCountService;
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

        loginCountService.increment();
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

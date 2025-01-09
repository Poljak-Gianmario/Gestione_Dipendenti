package com.poljak.gestionedipendenti.repository;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import static com.poljak.gestionedipendenti.service.login.PasswordHashService.compareHash;

@Repository
public class LoginRepository {

    private final JdbcTemplate jdbcTemplate;

    public LoginRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean matchCredenziali(String email, String password) {

        String retrieveEmailCount = "SELECT COUNT(*) FROM utenti WHERE email = ?";

        int count = jdbcTemplate.queryForObject(retrieveEmailCount, new Object[]{email}, Integer.class);

        if(count == 0)
            return false;

        String retrievePassword = "SELECT password FROM utenti WHERE email = ?" ;

        String hashed = jdbcTemplate.queryForObject(retrievePassword, new Object[]{email}, String.class);

        return compareHash(password, hashed);

    }

}

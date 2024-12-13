package org.example.gestione_dipendenti.Model.Repository;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import static org.example.gestione_dipendenti.Security.PasswordHash.compareHash;

@Repository
public class LoginRepository {

    private final JdbcTemplate jdbcTemplate;

    public LoginRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean matchCredenziali(String email, String password) {

        String retrive_email = "SELECT email FROM utenti WHERE email = ?" ;

        String r_email = jdbcTemplate.queryForObject(retrive_email, new Object[]{email}, String.class);

        if(r_email.isEmpty())
            return false;
        else {
            String retrive_password = "SELECT password FROM utenti WHERE email = ?" ;

            String hashed = jdbcTemplate.queryForObject(retrive_password, new Object[]{email}, String.class);

            if(compareHash(password,hashed))
                return true;

        }
        System.out.println("email:" + r_email + "password" + password);

        return false;
    }

}

package com.poljak.gestionedipendenti.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import static com.poljak.gestionedipendenti.service.login.PasswordHashService.hashPassword;

@Repository
public class SubscribeRepository {

    private final JdbcTemplate jdbcTemplate;

    public SubscribeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean aggiungiUtente(String nome, String cognome, String email, String password,String azienda){

        String verificaEmailQuery = "SELECT email FROM utenti WHERE email = ?";

        try {

            jdbcTemplate.queryForObject(verificaEmailQuery, new Object[]{email}, String.class);

            return false;
        } catch (EmptyResultDataAccessException e) {

            String hashPassword = hashPassword(password);

            String sql = "INSERT INTO utenti (nome, cognome, email, password, azienda) VALUES (?,?,?,?,?)";
            jdbcTemplate.update(sql, nome, cognome, email, hashPassword, azienda);  // Insert the new user

            return true;
        }




    }


}

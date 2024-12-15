package com.poljak.gestionedipendenti.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import static com.poljak.gestionedipendenti.service.login.PasswordHashService.hashPassword;

@Repository
public class SubscribeRepository {

    private final JdbcTemplate jdbcTemplate;

    public SubscribeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void aggiungiUtente(String nome, String cognome, String email, String password,String azienda){

        String hashPassword = hashPassword(password);

       String sql = "INSERT INTO utenti (nome,cognome,email,password,azienda) VALUES (?,?,?,?,?)";
       jdbcTemplate.update(sql,nome,cognome,email,hashPassword,azienda);
    }


}

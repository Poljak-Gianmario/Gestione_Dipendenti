package com.poljak.gestionedipendenti.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UtenteRepository {

    private final JdbcTemplate jdbcTemplate;

    public UtenteRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public String getNome(String email){

        String sql = "SELECT azienda FROM utenti WHERE email = ?";

        String azienda = (String) jdbcTemplate.queryForObject(sql, new Object[] {email}, String.class);

        return azienda;
    }


}

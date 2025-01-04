package com.poljak.gestionedipendenti.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UtenteRepository {

    private final JdbcTemplate jdbcTemplate;

    public UtenteRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public String getAzienda(String email){

        String sql = "SELECT azienda FROM utenti WHERE email = ?";

        return jdbcTemplate.queryForObject(sql, new Object[] {email}, String.class);
    }

    public int getId(String email){

        String sql = "SELECT id FROM utenti WHERE email = ?";

        return jdbcTemplate.queryForObject(sql, new Object[] {email}, Integer.class);
    }


}

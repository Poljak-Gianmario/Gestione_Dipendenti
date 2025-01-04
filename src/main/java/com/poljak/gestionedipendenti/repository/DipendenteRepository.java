package com.poljak.gestionedipendenti.repository;

import com.poljak.gestionedipendenti.controller.general.UtenteController;
import com.poljak.gestionedipendenti.model.Dipendente;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DipendenteRepository{

    private final JdbcTemplate jdbc;
    private final UtenteController utenteController;
    public DipendenteRepository(JdbcTemplate jdbc, UtenteController utenteController){
        this.utenteController = utenteController;
        this.jdbc = jdbc;
    }

    public boolean aggiungiDipendente(Dipendente dipendente, String email) {

        int id_utente = utenteController.getId(email);


        String sql = "INSERT INTO dipendenti (NOME, COGNOME, N_BADGE, RAL, id_utente) " +
                "SELECT ?, ?, ?, ?, ? " +
                "WHERE NOT EXISTS (SELECT 1 FROM dipendenti WHERE N_BADGE = ? AND id_utente = ?)";


        int val = jdbc.update(sql, dipendente.getNome(), dipendente.getCognome(), dipendente.getN_badge(), dipendente.getRal(), id_utente, dipendente.getN_badge(), id_utente);

        return val == 1;
    }


    public List<Dipendente> getDipendenti(String email){

        String sql = """
        SELECT d.id_utente, d.nome, d.cognome, d.n_badge, d.ral, d.id
        FROM dipendenti d
        JOIN utenti u ON d.id_utente = u.id
        WHERE u.email = ?
        """;

        RowMapper<Dipendente> dipendenteRowMapper = (r, i) -> {
            Dipendente rowObject = new Dipendente();
            rowObject.setId(r.getLong("id"));
            rowObject.setNome(r.getString("nome"));
            rowObject.setCognome(r.getString("cognome"));
            rowObject.setN_badge(r.getInt("n_badge"));
            rowObject.setRal(r.getInt("ral"));
            rowObject.setId_utente(r.getInt("id_utente"));

            return rowObject;
        };

        return jdbc.query(sql,dipendenteRowMapper,email);
    }

    public Dipendente getDipendente(int n_badge, String email){

        int id_utente = utenteController.getId(email);

        String sql = """
                SELECT d.id_utente, d.nome, d.cognome, d.n_badge, d.ral FROM dipendenti d WHERE n_badge = ? AND id_utente = ?
                """;

        RowMapper<Dipendente> dipendenteRowMapper = (r, i) -> {
            Dipendente rowObject = new Dipendente();
            rowObject.setNome(r.getString("nome"));
            rowObject.setCognome(r.getString("cognome"));
            rowObject.setN_badge(r.getInt("n_badge"));
            rowObject.setRal(r.getInt("ral"));

            return rowObject;
        };

        return jdbc.queryForObject(sql,dipendenteRowMapper,n_badge,id_utente);
    }

    public boolean eliminaDipendente(int n_badge, String email){

        int id_utente = utenteController.getId(email);
        String sql = "DELETE FROM dipendenti WHERE n_badge = ? AND id_utente = ?";

        return jdbc.update(sql,n_badge, id_utente) == 1;
    }

}

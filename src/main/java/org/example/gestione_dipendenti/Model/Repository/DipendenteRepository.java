package org.example.gestione_dipendenti.Model.Repository;

import org.example.gestione_dipendenti.Model.Dipendente;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DipendenteRepository{

    private final JdbcTemplate jdbc;

    public DipendenteRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public void InserisciDipendente(Dipendente dipendente){
        String sql = "INSERT INTO dipendenti VALUES (DEFAULT, ?,?,?,?)";

        jdbc.update(sql,dipendente.getNome(),dipendente.getCognome(),dipendente.getN_badge(),dipendente.getRal());
    }

    public List<Dipendente> Tutti(){

        String sql = "SELECT * FROM dipendenti";

        RowMapper<Dipendente> dipendenteRowMapper = (r, i) -> {
            Dipendente rowObject = new Dipendente();
            rowObject.setId(r.getLong("id"));
            rowObject.setNome(r.getString("nome"));
            rowObject.setCognome(r.getString("cognome"));
            rowObject.setN_badge(r.getInt("n_badge"));
            rowObject.setRal(r.getInt("ral"));

            return rowObject;
        };

        return jdbc.query(sql, dipendenteRowMapper);
    }

}
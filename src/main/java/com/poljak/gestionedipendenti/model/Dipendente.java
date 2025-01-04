package com.poljak.gestionedipendenti.model;

import jakarta.persistence.*;

@Entity
@Table
public class Dipendente {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String nome;
    private String cognome;
    private int n_badge;
    private int ral;



    private int id_utente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getN_badge() {
        return n_badge;
    }

    public void setN_badge(int n_badge) {
        this.n_badge = n_badge;
    }

    public int getRal() {
        return ral;
    }

    public void setRal(int ral) {
        this.ral = ral;
    }

    public int getId_utente() { return id_utente; }

    public void setId_utente(int id_utente) { this.id_utente = id_utente; }




}

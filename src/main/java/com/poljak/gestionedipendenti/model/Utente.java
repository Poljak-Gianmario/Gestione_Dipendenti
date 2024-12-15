package com.poljak.gestionedipendenti.model;

import jakarta.persistence.*;

@Entity
@Table
public class Utente {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String azienda;

}

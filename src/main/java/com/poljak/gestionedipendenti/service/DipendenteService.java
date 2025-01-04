package com.poljak.gestionedipendenti.service;

import com.poljak.gestionedipendenti.model.Dipendente;
import com.poljak.gestionedipendenti.repository.DipendenteRepository;
import com.poljak.gestionedipendenti.repository.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipendenteService {

    private final DipendenteRepository dipendenteRepository;
    private final LoginRepository loginRepository;
    public DipendenteService(DipendenteRepository dipendenteRepository, LoginRepository loginRepository){
        this.dipendenteRepository = dipendenteRepository;
        this.loginRepository = loginRepository;
    }

    public boolean aggiungiDipendente(Dipendente dipendente, String email){

        return dipendenteRepository.aggiungiDipendente(dipendente, email);

    }

    public List<Dipendente> getDipendenti(String email){
        return dipendenteRepository.getDipendenti(email);
    }

    public boolean eliminaDipendente(int n_badge, String email){
        return dipendenteRepository.eliminaDipendente(n_badge, email);
    }

    public boolean apiEliminaDipendente(int n_badge, String email, String password) throws PasswordMatchException {

        if(loginRepository.matchCredenziali(email, password)){
           return dipendenteRepository.eliminaDipendente(n_badge,email);
        }

        throw new PasswordMatchException("Le credenziali sono errate");

    }
    public boolean apiAggiungiDipendente(Dipendente dipendente, String email, String password) throws PasswordMatchException {


        if(loginRepository.matchCredenziali(email, password)){
            return dipendenteRepository.aggiungiDipendente(dipendente, email);
        }

        throw new PasswordMatchException("Le credenziali sono errate");

    }

    public List<Dipendente> apiGetDipendenti(String email, String password) throws PasswordMatchException {

        if(loginRepository.matchCredenziali(email,password)){
            return dipendenteRepository.getDipendenti(email);
        }

        throw new PasswordMatchException("Le credenziali sono errate");
    }

    public Dipendente apiGetDipendente(int n_badge, String email, String password) throws PasswordMatchException {

        if(loginRepository.matchCredenziali(email,password)){
            return dipendenteRepository.getDipendente(n_badge,email);
        }

        throw new PasswordMatchException("Le credenziali sono errate");

    }






}

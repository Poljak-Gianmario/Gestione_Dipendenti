package com.poljak.gestionedipendenti.controller.api;

import com.poljak.gestionedipendenti.model.Dipendente;
import com.poljak.gestionedipendenti.service.DipendenteService;
import com.poljak.gestionedipendenti.service.PasswordMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dipendenti")
public class ApiDipendente {

    private final DipendenteService dipendenteService;
    public ApiDipendente(DipendenteService dipendenteService){
        this.dipendenteService = dipendenteService;
    }
    @GetMapping
    public ResponseEntity<List<Dipendente>> getDipendenti(@RequestHeader String email,@RequestHeader String password) throws PasswordMatchException {

        List<Dipendente> dipendenti = dipendenteService.apiGetDipendenti(email,password);
        return new ResponseEntity<>(dipendenti,HttpStatus.OK); // 200 OK
    }

    @DeleteMapping
    public ResponseEntity<String> elimina(int n_badge, @RequestHeader String email,@RequestHeader String password) throws PasswordMatchException {

        if(dipendenteService.apiEliminaDipendente(n_badge, email, password)){
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return  ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @PostMapping
    public ResponseEntity<String> aggiungi(@RequestBody Dipendente dipendente, @RequestHeader String email, @RequestHeader String password) throws PasswordMatchException {

        if(dipendenteService.apiAggiungiDipendente(dipendente,email,password)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Il dipendente è stato aggiunto con successo"); // 201 Created
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Il dipendente non è stato inserito"); // 400 Bad Request
        }

    }

    @GetMapping("/dipendente")
    public ResponseEntity<Dipendente> getDipendente(int n_badge,@RequestHeader String email,@RequestHeader String password) throws PasswordMatchException {
        Dipendente dipendente = dipendenteService.apiGetDipendente(n_badge, email, password);

        return new ResponseEntity<>(dipendente, HttpStatus.OK); // 200 OK
    }

}

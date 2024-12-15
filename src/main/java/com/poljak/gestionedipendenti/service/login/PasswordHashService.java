package com.poljak.gestionedipendenti.service.login;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashService {

    public static String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static boolean compareHash(String password, String hash){
        return BCrypt.checkpw(password, hash);
    }

}

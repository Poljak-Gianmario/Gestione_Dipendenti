package org.example.gestione_dipendenti.Security;

import org.mindrot.jbcrypt.BCrypt;

import static java.lang.System.err;

public class PasswordHash {

    public static String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static boolean compareHash(String password, String hash){
        return BCrypt.checkpw(password, hash);
    }

}

package fr.ensicaen.tennis;

import org.mindrot.jbcrypt.BCrypt;


public class HashPasswordExample {
    public static void main(String[] args) {
        String password = "a";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

        System.out.println("Mot de passe haché : " + hashedPassword);
    }
}
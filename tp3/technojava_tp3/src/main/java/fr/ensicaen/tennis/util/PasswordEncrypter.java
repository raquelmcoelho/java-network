package fr.ensicaen.tennis.util;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncrypter {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public static boolean checkPassword(String password, String storedHash) {
        return BCrypt.checkpw(password, storedHash);
    }
}

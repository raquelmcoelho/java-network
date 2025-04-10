package fr.ensicaen.dp.tennis.appli;


import fr.ensicaen.dp.tennis.entities.AdherentEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class LoginCheck {
    private static EntityManager entityManager ;
    public LoginCheck() {
        entityManager = Persistence.createEntityManagerFactory("TennisUnit").createEntityManager();
    }

    public static boolean validate(String name,String pass){
        boolean status=false;
        try {
            entityManager = Persistence.createEntityManagerFactory("TennisUnit").createEntityManager();
            Query query = entityManager.createQuery("from AdherentEntity where UPPER(nom) LIKE UPPER('" + name + "')");
            ArrayList<AdherentEntity> list = (ArrayList<AdherentEntity>) query.getResultList();
            for (AdherentEntity adherent : list) {
                if(adherent.getPassword().equals(hashValue(pass))) {
                    System.out.println("authentified");
                    status = true;
                }else {
                    System.out.println("unknown user, failed to login !");
                }
            }
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        return status;
    }

    public static String hashValue(String passwordToHash) {
        String generatedPassword = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

}
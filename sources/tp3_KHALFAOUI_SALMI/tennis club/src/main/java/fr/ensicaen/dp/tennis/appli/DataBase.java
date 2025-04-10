package fr.ensicaen.dp.tennis.appli;

import fr.ensicaen.dp.tennis.entities.AdherentEntity;
import fr.ensicaen.dp.tennis.entities.InscriptionEntity;
import fr.ensicaen.dp.tennis.entities.TournoiEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;


public class DataBase {
    private EntityManager entityManager;

    public DataBase() {
        entityManager = Persistence.createEntityManagerFactory("TennisUnit").createEntityManager();
    }

    public ArrayList<TournoiEntity> getTournamentList(ArrayList<String> codes) {
        Query query;
        if(codes.size() != 0) {
            String list = "('" + codes.get(0) + "'";
            for (int i = 1; i < codes.size(); i++) {
                list += ", '" + codes.get(i) + "'";
            }
            list += ")";
            query = entityManager.createQuery("from TournoiEntity where codetournoi not in " + list);
        } else {
            query = entityManager.createQuery("from TournoiEntity");
        }
        return (ArrayList<TournoiEntity>) query.getResultList();
    }
    public AdherentEntity getPersonalInformation(String id) {
        Query query = entityManager.createQuery("from AdherentEntity where UPPER(nom) LIKE UPPER('" + id + "')");
        System.out.println(id);
        return (AdherentEntity) query.getResultList().get(0);
    }

    public ArrayList<InscriptionEntity> getInscription(String id) {
        Query query = entityManager.createQuery("from InscriptionEntity where numeroadherent LIKE '" + id + "'");
        System.out.println(id);
        return (ArrayList<InscriptionEntity>) query.getResultList();
    }

    public TournoiEntity getTournament(String id) {
        Query query = entityManager.createQuery("from TournoiEntity where codetournoi LIKE '" + id + "'");
        System.out.println(id);
        return (TournoiEntity) query.getResultList().get(0);
    }

    public void delete_Adherent(String id, String numb) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM InscriptionEntity WHERE codetournoi = :code and numeroadherent = :num");
        query.setParameter("code", id);
        query.setParameter("num", numb);
        int rowsDeleted = query.executeUpdate();
        System.out.println("entities deleted: " + rowsDeleted);
        entityManager.getTransaction().commit();
    }

    public void insert_Adherent(String codetournoi, String numeroadherent) {
        LocalDate todaysDate = LocalDate.now();
        //Query query = entityManager.createQuery("INSERT into InscriptionEntity values('"+ numb +"','"+ id +"','" + todaysDate + "')");
        Query query = entityManager.createQuery("INSERT into InscriptionEntity(numeroadherent, codetournoi, dateinscription)"
                + "select a.numeroadherent, t.codetournoi, t.date from TournoiEntity as t, AdherentEntity as a where  a.numeroadherent='"
                + numeroadherent +"' and t.codetournoi='" + codetournoi +"'");
        entityManager.getTransaction().begin();
        int rowsIns = query.executeUpdate();
        System.out.println("entities deleted: " + rowsIns);
        entityManager.getTransaction().commit();
    }
}

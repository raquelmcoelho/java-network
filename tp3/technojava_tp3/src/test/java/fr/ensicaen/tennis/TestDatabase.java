package fr.ensicaen.tennis;

import fr.ensicaen.tennis.persistence.AdherentEntity;
import fr.ensicaen.tennis.persistence.TournoiEntity;
import fr.ensicaen.tennis.persistence.InscriptionEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.sql.Date;

public class TestDatabase {

	private static EntityManager entityManager;

	@BeforeAll
	public static void before() {
		final String db_unit_name = ApplicationProperties.get("fr/ensicaen/tennis");
		entityManager = Persistence.createEntityManagerFactory(db_unit_name).createEntityManager();

		AdherentEntity adherent = new AdherentEntity();
		adherent.setNom("TestNom");
		adherent.setPrenom("TestPrenom");
		adherent.setAdresse("123 Rue");
		adherent.setTelephone("0123456789");
		adherent.setEmail("test@ensicaen.fr");
		adherent.setPassword("secret");

		TournoiEntity tournoi = new TournoiEntity();
		tournoi.setNom("Test Tournoi");
		tournoi.setDate(Date.valueOf(LocalDate.now()));
		tournoi.setLieu("Caen");

		entityManager.getTransaction().begin();
		entityManager.persist(adherent);
		entityManager.persist(tournoi);
		entityManager.getTransaction().commit();

		InscriptionEntity inscription = new InscriptionEntity();
		inscription.setNumeroAdherent(adherent.getNumeroAdherent());
		inscription.setCodeTournoi(tournoi.getCodeTournoi());
		inscription.setDateInscription(Date.valueOf(LocalDate.now()));

		entityManager.getTransaction().begin();
		entityManager.persist(inscription);
		entityManager.getTransaction().commit();
	}

	@Test
	public void database_engine_ok() {
		assert entityManager != null;
	}

	@Test
	public void get_adherent_by_email() {
		Query query = entityManager.createQuery("FROM AdherentEntity a WHERE a.email LIKE :email");
		query.setParameter("email", "%ensicaen.fr");
		assert !query.getResultList().isEmpty();
	}

	@Test
	public void check_inscription_exists() {
		Query query = entityManager.createQuery("FROM InscriptionEntity i");
		assert !query.getResultList().isEmpty();
	}

	@AfterAll
	public static void after() {
		if (entityManager.isOpen()) {
			entityManager.close();
		}
	}
}

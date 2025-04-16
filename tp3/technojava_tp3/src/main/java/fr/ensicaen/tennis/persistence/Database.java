package fr.ensicaen.tennis.persistence;

import fr.ensicaen.tennis.security.XSSRequestWrapper;
import fr.ensicaen.tennis.ApplicationProperties;
import fr.ensicaen.tennis.util.Logger;
import fr.ensicaen.tennis.util.TournoiInscriptionDTO;
import jakarta.persistence.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author JRD
 * @see <a href="https://javadoc.io/doc/jakarta.persistence/jakarta.persistence-api/3.0.0/jakarta.persistence/module-summary.html">Jakarta Persistence API</a>
 */
public class Database {
	private static Database instance;
	private EntityManagerFactory emf;
	private EntityManager entityManager;
	private static final Logger logger = new Logger(Database.class.getName());

	private static final String [] DISPLAY_PROPERTIES = {
		"user.dir", "jakarta.persistence.jdbc.url","hibernate.persistenceUnitName", "hibernate.connection.driver_class", "hibernate.dialect"};

	public static Database getInstance(String realPath) {
		if (instance == null) instance = new Database(realPath);
		return instance;
	}
	public static Database getInstance() {
		return getInstance(null);
	}

	protected Database() {
		createAndOpenDB(null);
	}
	protected Database(String dbURL) {
		createAndOpenDB(dbURL);
	}

	private void createAndOpenDB(String realPath) {
		final String db_unit_name = ApplicationProperties.get("db_unit_name");
		// Override url property
		Properties props = new Properties();
		props.setProperty("jakarta.persistence.jdbc.url", "jdbc:h2:file:"+realPath+"/WEB-INF/db/tennis;LOCK_MODE=0;DB_CLOSE_DELAY=0;");
		// Create persistence unit
		emf = Persistence.createEntityManagerFactory(db_unit_name, props);
		logger.info("Open persistence unit : " + db_unit_name);
		logger.config("------ ENTITY MANAGER PROPERTIES");
		Map<String, Object> properties = emf.getProperties();
		Arrays.stream(DISPLAY_PROPERTIES).forEach(key -> logger.config(key+"="+properties.get(key)));
//		properties.keySet().stream().filter(s -> s.contains("javax.persistence.jdbc")).forEach(k -> System.out.println(k+"="+properties.get(k)));
		logger.config("----------------------------------");
		// Create entity manager (query database from this)
		entityManager = emf.createEntityManager();
	}

	public void close() {
		logger.info("Close persistence ...");
		if (entityManager.isOpen()) entityManager.close();
		if (emf.isOpen()) emf.close();
	}

	/**
	 * Anti-injection method.
	 */
	public static String XSSReplacer(String s) {
		for (Pattern scriptPattern : XSSRequestWrapper.patterns){
			s = scriptPattern.matcher(s).replaceAll("");
		}
		return s;
	}

	public List<AdherentEntity> listAdherents() {
		final TypedQuery<AdherentEntity> query = entityManager.createQuery("from AdherentEntity t", AdherentEntity.class);
		return query.getResultList();
	}

	public Optional<AdherentEntity> getAdherentByEmail(String email) {
		final TypedQuery<AdherentEntity> query = entityManager.createQuery("from AdherentEntity t where t.email like :email", AdherentEntity.class);
		query.setParameter("email", email);
		return query.getResultList().stream().findFirst();
	}

	public List<TournoiEntity> listTournois() {
		final TypedQuery<TournoiEntity> query = entityManager.createQuery("from TournoiEntity t", TournoiEntity.class);
		return query.getResultList();
	}

	public TournoiEntity getTournoiByCode(int code) {
		return entityManager.find(TournoiEntity.class, code);
	}

	public boolean adherentAlreadyRegisteredForTournoi(int codeTournoi, int numeroAdherent) {
		final Query query = entityManager.createQuery(
				"select count(*) from InscriptionEntity i " +
						"where i.codeTournoi = :codeTournoi " +
						"and i.numeroAdherent = :numeroAdherent"
		);
		query.setParameter("codeTournoi", codeTournoi);
		query.setParameter("numeroAdherent", numeroAdherent);
		Long count = (Long) query.getSingleResult();

		return (count > 0);
	}

	public List<TournoiInscriptionDTO> getTournoiInfosByAdherent(int numeroAdherent) {
		AdherentEntity adherent = entityManager.find(AdherentEntity.class, numeroAdherent);

		TypedQuery<InscriptionEntity> query = entityManager.createQuery(
				"from InscriptionEntity i where i.numeroAdherent = :numeroAdherent", InscriptionEntity.class);
		query.setParameter("numeroAdherent", adherent.getNumeroAdherent());
		List<InscriptionEntity> inscriptions = query.getResultList();

		List<TournoiInscriptionDTO> dtos = inscriptions.stream()
				.map(inscription -> {
					TournoiEntity tournoi = entityManager.find(TournoiEntity.class, inscription.getCodeTournoi());
					TournoiInscriptionDTO dto = new TournoiInscriptionDTO();
					dto.codeTournoi = tournoi.getCodeTournoi();
					dto.nomTournoi = tournoi.getNom();
					dto.lieuTournoi = tournoi.getLieu();
					dto.dateTournoi = new SimpleDateFormat("dd/MM/yy").format(tournoi.getDate());
					dto.dateInscription = new SimpleDateFormat("dd/MM/yy").format(inscription.getDateInscription());
					return dto;
				})
				.collect(Collectors.toList());

		return dtos;
	}

	public boolean registerAdherentTo(int codeTournoi, int numeroAdherent) {
		TournoiEntity tournoi = entityManager.find(TournoiEntity.class, codeTournoi);

		if (tournoi != null) {
			InscriptionEntity inscription = new InscriptionEntity();
			inscription.setNumeroAdherent(numeroAdherent);
			inscription.setCodeTournoi(codeTournoi);
			inscription.setDateInscription(new Date());

			entityManager.getTransaction().begin();
			entityManager.persist(inscription);
			entityManager.getTransaction().commit();
			return true;
		} else {
			return false;
		}
	}
}

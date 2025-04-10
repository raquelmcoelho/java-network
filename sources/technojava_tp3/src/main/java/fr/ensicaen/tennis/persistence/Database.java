package fr.ensicaen.tennis.persistence;

//import fr.ensicaen.tennis.exception.AuthenticationException;
//import fr.ensicaen.tennis.utils.PwdUtils;
import fr.ensicaen.tennis.security.XSSRequestWrapper;
import fr.ensicaen.tennis.ApplicationProperties;
import fr.ensicaen.tennis.util.Logger;
import jakarta.persistence.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

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

	// ********** TODOLIST **********

	public List<TodoEntity> listTodo() {
		final TypedQuery<TodoEntity> query = entityManager.createQuery("from TodoEntity t", TodoEntity.class);
		return query.getResultList();
	}

	public TodoEntity getTodoById(int id) {
		final Query query = entityManager.createQuery("from TodoEntity t where t.idTodo = :id");
		query.setParameter("id", id);
		return (TodoEntity)query.getSingleResult();
	}

	public TodoEntity addTodoByDescription(String description) {
		if (description == null || description.isEmpty()) return null;
		TodoEntity todo = new TodoEntity();
		todo.setDescription(XSSReplacer(description));
		entityManager.getTransaction().begin();
		entityManager.persist(todo);
		entityManager.getTransaction().commit();
		return todo;
	}

	// ********** ADHERENTLIST **********

	public List<AdherentEntity> listAdherent() {
		final TypedQuery<AdherentEntity> query = entityManager.createQuery("from AdherentEntity a", AdherentEntity.class);
		return query.getResultList();
	}

	public AdherentEntity getAdherentByUsername(String username) {
		final Query query = entityManager.createQuery("from AdherentEntity a where a.email = :username");
		query.setParameter("username", username);
		return (AdherentEntity) query.getSingleResultOrNull();
	}

	public List<TournoiEntity> listRegisteredTournament(int adherent) {
		final TypedQuery<TournoiEntity> query = entityManager.createQuery("FROM TournoiEntity t JOIN InscriptionEntity i ON t.codeTournoi = i.codeTournoi WHERE i.numeroAdherent = :adherent", TournoiEntity.class);
		query.setParameter("adherent", adherent);
		return query.getResultList();
	}

	public List<TournoiEntity> listTournament() {
		final TypedQuery<TournoiEntity> query = entityManager.createQuery("FROM TournoiEntity", TournoiEntity.class);
		return query.getResultList();
	}

	public InscriptionEntity addTournoi(int numeroAdherent, int codeTournoi) {
		InscriptionEntity inscription = new InscriptionEntity();
		inscription.setCodeTournoi(Integer.parseInt(XSSReplacer(String.valueOf(codeTournoi))));
		inscription.setAdherent(Integer.parseInt(XSSReplacer(String.valueOf(numeroAdherent))));
		Date date = Date.valueOf(LocalDate.now());
		inscription.setDateInscription(date);
		entityManager.getTransaction().begin();
		entityManager.persist(inscription);
		entityManager.getTransaction().commit();
		return inscription;
	}


}

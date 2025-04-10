package fr.ensicaen.tennis.bean;

import fr.ensicaen.tennis.persistence.AdherentEntity;
import fr.ensicaen.tennis.persistence.Database;
import fr.ensicaen.tennis.persistence.InscriptionEntity;
import fr.ensicaen.tennis.persistence.TournoiEntity;
import fr.ensicaen.tennis.util.Logger;

import java.util.List;

public class AdherentBean {
	private final Database database;
	private final static Logger logger = new Logger(AdherentBean.class.getName());
	public AdherentBean() {
		database = Database.getInstance();
		logger.debug("Loading bean.");
	}

	public List<AdherentEntity> getTodoList() {
		return database.listAdherent();
	}
	public AdherentEntity getAdherentByUsername(String username) {
		return database.getAdherentByUsername(username);
	}
	public List<TournoiEntity> getListTournoiOf(AdherentEntity adherent) {return database.listRegisteredTournament(adherent.getNumeroAdherent());}
	public List<TournoiEntity> getListTournoi() {return database.listTournament();}
	public InscriptionEntity registerTournoi(int numeroAdherent, int codeTournoi) {return database.addTournoi(numeroAdherent, codeTournoi);}
}

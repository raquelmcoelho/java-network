package fr.ensicaen.tennis.bean;

import fr.ensicaen.tennis.persistence.AdherentEntity;
import fr.ensicaen.tennis.persistence.Database;
import fr.ensicaen.tennis.persistence.TodoEntity;
import fr.ensicaen.tennis.util.Logger;
import fr.ensicaen.tennis.util.TournoiInscriptionDTO;

import java.util.List;

public class AdherentBean {
    private final Database database;
    private final static Logger logger = new Logger(AdherentBean.class.getName());
    public AdherentBean() {
        database = Database.getInstance();
        logger.debug("Loading bean.");
    }

    public List<TournoiInscriptionDTO> getTournoiInfosByAdherent(int numeroAdherent) {
        return database.getTournoiInfosByAdherent(numeroAdherent);
    }
}

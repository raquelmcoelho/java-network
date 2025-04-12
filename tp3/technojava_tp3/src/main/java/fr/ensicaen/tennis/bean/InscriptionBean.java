package fr.ensicaen.tennis.bean;

import fr.ensicaen.tennis.persistence.Database;
import fr.ensicaen.tennis.util.Logger;

public class InscriptionBean {
    private final Database database;
    private final static Logger logger = new Logger(InscriptionBean.class.getName());
    public InscriptionBean() {
        database = Database.getInstance();
        logger.debug("Loading bean.");
    }

    public boolean isAlreadyDone(int codeTournoi, int numeroAdherent)
    {
        return database.adherentAlreadyRegisteredForTournoi(codeTournoi, numeroAdherent);
    }
}

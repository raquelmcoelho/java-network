package fr.ensicaen.tennis.bean;

import fr.ensicaen.tennis.persistence.Database;
import fr.ensicaen.tennis.persistence.TournoiEntity;
import fr.ensicaen.tennis.util.Logger;

import java.util.List;

public class TournoiBean {
    private final Database database;
    private final static Logger logger = new Logger(TournoiBean.class.getName());
    public TournoiBean() {
        database = Database.getInstance();
        logger.debug("Loading bean.");
    }

    public List<TournoiEntity> getTournoiList() {
        return database.listTournois();
    }
}


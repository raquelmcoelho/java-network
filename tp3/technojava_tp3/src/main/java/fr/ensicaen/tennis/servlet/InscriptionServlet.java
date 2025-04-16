package fr.ensicaen.tennis.servlet;

import fr.ensicaen.tennis.persistence.AdherentEntity;
import fr.ensicaen.tennis.persistence.Database;
import fr.ensicaen.tennis.persistence.TournoiEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="inscription", urlPatterns = "/service/inscription")
public class InscriptionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database database = Database.getInstance();
        String codeTournoiStr = req.getParameter("tournoi");
        int numeroAdherent = ((AdherentEntity) req.getSession().getAttribute("adherent")).getNumeroAdherent();

        if (codeTournoiStr == null) {
            req.setAttribute("numeroAdherent", numeroAdherent);
            req.getRequestDispatcher("/InscriptionTournois.jsp").forward(req, resp);
            return;
        }

        int codeTournoi = Integer.parseInt(codeTournoiStr);
        boolean success = database.registerAdherentTo(codeTournoi, numeroAdherent);

        if(success) {
            req.setAttribute("status", "Inscription réussie pour le tournoi");
        }

        req.setAttribute("success", success);
        req.setAttribute("tournoi", database.getTournoiByCode(codeTournoi));
        req.getRequestDispatcher("/InscriptionStatus.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

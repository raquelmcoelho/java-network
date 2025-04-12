package fr.ensicaen.tennis.servlet;

import fr.ensicaen.tennis.persistence.AdherentEntity;
import fr.ensicaen.tennis.persistence.Database;
import fr.ensicaen.tennis.persistence.TournoiEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="inscription", urlPatterns = "/service/inscription")
public class InscriptionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codeTournoi = req.getParameter("tournoi");

        if (codeTournoi == null) {
            req.getRequestDispatcher("/InscriptionTournois.jsp").forward(req, resp);
            return;
        }

        // Cas: Enregistrement de l'inscription
        int code = Integer.parseInt(codeTournoi);
        AdherentEntity adherent = (AdherentEntity) req.getSession().getAttribute("adherent");
        if(Database.getInstance().registerAdherentTo(code, adherent.getNumeroAdherent())) {
                req.setAttribute("status", "Inscription réussie pour le tournoi");
        } else {
            req.setAttribute("status", "La tentative a échoué");
        }

        req.getRequestDispatcher("/InscriptionStatus.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

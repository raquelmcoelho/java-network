package fr.ensicaen.tennis.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="inscription", urlPatterns = "/service/inscription")
public class InscriptionServlet extends HttpServlet {
//    InscriptionServlet :
//    Paramètre de la requête : « Code tournoi » ( tournoi=xxx )
//            2 cas de figure :
//            - pas de « code tournoi », afficher la liste de tous les tournois pour
//    inscription éventuelle : forward à InscriptionTournois.jsp
//- « code tournoi » présent, enregistrer l’inscription puis forward
//InscriptionStatus.jsp (pour afficher l’état de l’inscription)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}

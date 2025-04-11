package fr.ensicaen.tennis.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="adherent", urlPatterns = "/service/adherent")
public class AdherentServlet extends HttpServlet {
//    A dherentServlet :
//    Préparation des informations relatives à l’adhérent (informations
//    personnelles, liste des tournois auxquels il est inscrit) : forward -> Adherent.jsp
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}

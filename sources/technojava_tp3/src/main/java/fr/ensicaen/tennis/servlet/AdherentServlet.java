package fr.ensicaen.tennis.servlet;

import fr.ensicaen.tennis.persistence.AdherentEntity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="adherent", urlPatterns = "/adherent")
public class AdherentServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher requestDispatcher;

		AdherentEntity adherent = (AdherentEntity) req.getSession().getAttribute("adherent");
		requestDispatcher = req.getRequestDispatcher("/Adherent.jsp");
		req.setAttribute("adherent", adherent);
		requestDispatcher.forward(req, resp);


	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}

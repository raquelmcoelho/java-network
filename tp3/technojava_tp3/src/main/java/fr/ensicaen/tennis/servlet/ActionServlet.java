package fr.ensicaen.tennis.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="action", urlPatterns = "/action")
public class ActionServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		// - Vérifie que l’utilisateur (adhérent) possède une session active ( déjà
		// authentifié ) :

		// - Si non :
		// Si action == « L » ( « login » ) forward à LoginServlet
		// Sinon forward à Login.html

		// - Si oui :
		// Si action == « A » ( « dossier adherent » ) forward
		// 		àAdherentServlet
		// Si action == « I » ( « inscription tournoi » ) forward
		// 		àInscriptionServlet
		// - Autre cas (autre valeur ou pas de code action) forward à Menu.jsp

		// Cria ou pega a sessão
		HttpSession session = req.getSession(true);
//		session.setAttribute("adherent", adherent);
//		Adherent user = (Adherent) session.getAttribute("adherent");
		if (session.isNew()) {
			System.out.println("New session");
			session.setMaxInactiveInterval(10); // 5 min
		}

		RequestDispatcher rd = req.getRequestDispatcher("Login.html");
		rd.forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		PrintWriter w = resp.getWriter();
		w.println("<HTML><BODY>");
		w.println("<br/>Le code est : "+code);
		w.println("</BODY></HTML");
	}
}

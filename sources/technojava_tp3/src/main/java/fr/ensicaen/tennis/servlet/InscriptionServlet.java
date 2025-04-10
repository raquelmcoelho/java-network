package fr.ensicaen.tennis.servlet;

import fr.ensicaen.tennis.bean.AdherentBean;
import fr.ensicaen.tennis.persistence.AdherentEntity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="inscription", urlPatterns = "/inscription")
public class InscriptionServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String codeTournoi = req.getParameter("tournoi");
		AdherentEntity adherent = (AdherentEntity) req.getSession().getAttribute("adherent");
		RequestDispatcher requestDispatcher;

		if (codeTournoi != null) {
			int code = Integer.parseInt(codeTournoi);
			AdherentBean adherentBean = new AdherentBean();
			adherentBean.registerTournoi(adherent.getNumeroAdherent(), code);
			req.setAttribute("adherent", adherent);
			requestDispatcher = req.getRequestDispatcher("/AddTodo.jsp");
			requestDispatcher.forward(req, resp);

		} else {
			requestDispatcher = req.getRequestDispatcher("/AddTodo.jsp");
			req.setAttribute("adherent", adherent);
			requestDispatcher.forward(req, resp);
		}




	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}

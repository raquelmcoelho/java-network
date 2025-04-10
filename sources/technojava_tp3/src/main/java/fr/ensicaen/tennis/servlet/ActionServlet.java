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

@WebServlet(name="action", urlPatterns = "/action")
public class ActionServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		RequestDispatcher requestDispatcher;

		AdherentEntity adherent = (AdherentEntity) req.getSession().getAttribute("adherent");
		if (adherent == null) {
			if ("L".equals(code)) {
				requestDispatcher = req.getRequestDispatcher("/login");
				requestDispatcher.forward(req, resp);
			}
			else {
				requestDispatcher = req.getRequestDispatcher("/Login.jsp");
				requestDispatcher.forward(req, resp);
			}
			return;
		}


		switch (code) {

			case "A":
				requestDispatcher = req.getRequestDispatcher("/Adherent.jsp");
				requestDispatcher.forward(req, resp);
				break;
			case "I":
				requestDispatcher = req.getRequestDispatcher("/AddTodo.jsp");
				requestDispatcher.forward(req, resp);
				break;
			default:
				requestDispatcher = req.getRequestDispatcher("/Menu.jsp");
				requestDispatcher.forward(req, resp);

		}


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

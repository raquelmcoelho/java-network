package fr.ensicaen.tennis.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="action", urlPatterns = "/action")
public class ActionServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		String code = request.getParameter("code");

		boolean sessionStillValid = (session != null && session.getAttribute("adherent") != null);

		if (!sessionStillValid) {
			if ("L".equals(code)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/service/login");
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("/Login.jsp");
			}
			return;
		}

		switch (code) {
			case "A":
				request.getRequestDispatcher("/service/adherent").forward(request, response);
				break;
			case "I":
				request.getRequestDispatcher("/service/inscription").forward(request, response);
				break;
			default:
				request.getRequestDispatcher("/Menu.jsp").forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}

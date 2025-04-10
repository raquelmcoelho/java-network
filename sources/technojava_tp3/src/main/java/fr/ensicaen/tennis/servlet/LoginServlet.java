package fr.ensicaen.tennis.servlet;

import fr.ensicaen.tennis.bean.AdherentBean;
import fr.ensicaen.tennis.persistence.AdherentEntity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher requestDispatcher = null;
		AdherentBean adherentBean = new AdherentBean();

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println("usr : " + username);
		AdherentEntity adherent = adherentBean.getAdherentByUsername(username);
		if (adherent == null) {
			requestDispatcher = req.getRequestDispatcher("/Login.jsp");
			requestDispatcher.forward(req, resp);
			return;
		}

		String storedHashedPassword = adherent.getPassword();

		if (BCrypt.checkpw(password, storedHashedPassword)) {
			requestDispatcher = req.getRequestDispatcher("/Menu.jsp");
			req.getSession().setAttribute("adherent", adherent);
			requestDispatcher.forward(req, resp);
		} else {
			requestDispatcher = req.getRequestDispatcher("/Login.jsp");
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

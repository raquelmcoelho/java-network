package fr.ensicaen.tennis.servlet;

import fr.ensicaen.tennis.persistence.AdherentEntity;
import fr.ensicaen.tennis.persistence.Database;
import fr.ensicaen.tennis.util.PasswordEncrypter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="login", urlPatterns = "/service/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        AdherentEntity adherent = Database.getInstance().getAdherentByEmail(email);

        if (adherent != null && PasswordEncrypter.checkPassword(password, adherent.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("adherent", adherent);
            RequestDispatcher dispatcher = req.getRequestDispatcher("Menu.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("Login.html");
        }
    }
}

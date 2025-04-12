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
import java.util.Optional;

@WebServlet(name="login", urlPatterns = "/service/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Optional<AdherentEntity> adherent = Database.getInstance().getAdherentByEmail(email);

//        System.out.println("\n\nRESULT:" + adherent.isPresent());
//        if(adherent.isPresent()) {
//            System.out.println("RESULT:" + PasswordEncrypter.hashPassword(password));
//            System.out.println("RESULT:" + adherent.get().getPassword());
//            System.out.println("RESULT:" + PasswordEncrypter.checkPassword(password, adherent.get().getPassword()));
//        }

        if (adherent.isPresent() && PasswordEncrypter.checkPassword(password, adherent.get().getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("adherent", adherent.get());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Menu.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Login.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

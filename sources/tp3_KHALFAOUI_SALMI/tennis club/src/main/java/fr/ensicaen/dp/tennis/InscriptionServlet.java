package fr.ensicaen.dp.tennis;

import fr.ensicaen.dp.tennis.appli.DataBase;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InscriptionServlet", value = "/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destPage = "inscriptionTournaments.jsp";
        DataBase base = new DataBase();
        String code = request.getParameter("code");
        String id = request.getParameter("id");
        if(code != null) {
            base.insert_Adherent(code, id);
            System.out.println(request.getParameter("code"));
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request,response);
        System.out.println(request.getParameter("code"));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

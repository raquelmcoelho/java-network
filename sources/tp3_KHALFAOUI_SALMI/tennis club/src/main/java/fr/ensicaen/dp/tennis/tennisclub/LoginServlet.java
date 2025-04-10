package fr.ensicaen.dp.tennis.tennisclub;

import fr.ensicaen.dp.tennis.appli.LoginCheck;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        String destinationPage = "home.jsp";
        String n = request.getParameter("uname");
        String p = request.getParameter("psw");
        HttpSession session = request.getSession();
        session.setAttribute("name", n);
        if(LoginCheck.validate(n, p)){
            RequestDispatcher rd=request.getRequestDispatcher(destinationPage);
            rd.forward(request,response);
            out.println("<html><body>");
            out.println("<h1>" + "Hello my men " + n  + "</h1>");
            out.println("<a href=" + "index.jsp" + "> Tournament</a>");
            out.println("<a href=" + "info.jsp" + "> Personal Info</a>");
            out.println("</body></html>");
        }
        else{
            out.print("Sorry username or password error");
            RequestDispatcher rd=request.getRequestDispatcher("login.html");
            rd.include(request,response);
        }
        out.close();
    }

    public void destroy() {
    }
}
<%@ page import="fr.ensicaen.dp.tennis.appli.DataBase" %>
<%@ page import="fr.ensicaen.dp.tennis.entities.AdherentEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="fr.ensicaen.dp.tennis.entities.InscriptionEntity" %>
<%@ page import="fr.ensicaen.dp.tennis.entities.TournoiEntity" %>
<%@ page import="java.util.Objects" %>
<%@ page import="static java.lang.Integer.parseInt" %>

<%--
  Created by IntelliJ IDEA.
  User: khalfaoui
  Date: 15/03/2022
  Time: 08:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Info</title>
    <link rel="stylesheet" href="styles/info.css">
    <script type="text/javascript" src="js/info.js"></script>
</head>
<body>
<h1 style="text-align:center; color: #fefefe"><%= "Hello champion!" %>
</h1>
<br/>

<%
    DataBase base = new DataBase();
    session = request.getSession();
    String name = (String) session.getAttribute("name");
    String mytournaments = "<br><br><table class=\"center\"><tr><th>Code</th><th>Name</th><th>Date</th><th>Place</th><th>Stat</th></tr>";
    AdherentEntity myInfo = base.getPersonalInformation(name);

    out.println("<table class=\"center\"><tr><td>Last Name:</td><td>" + myInfo.getNom() +
            "</td></tr><tr><td>First Name:</td><td>" + myInfo.getPrenom() +
            "</td></tr><tr><td>Address:</td><td>" + myInfo.getAdresse() +
            "</td></tr><tr><td>Phone Number:</td><td>" + myInfo.getTelephone() +
            "</td></tr><tr><td>Email:</td><td>" + myInfo.getEmail() +
            "</td></tr></table>");
    String id = myInfo.getNumeroadherent();
    out.println("<h1 style=\"text-align:center; color:white;\">My tournaments!</h1>");
    ArrayList<InscriptionEntity> listTournaments = base.getInscription(id);
    for (int i = 0; i < listTournaments.size(); i++) {
        TournoiEntity tournament = base.getTournament(listTournaments.get(i).getCodetournoi());
        mytournaments += "<tr><td>" + listTournaments.get(i).getCodetournoi() + "</td><td>" + tournament.getNom() +
                "</td><td>" + tournament.getDate() + "</td><td>" + tournament.getLieu() +
                "</td><td><a id='bu' href=\"AdherentServlet?code=" + tournament.getCodetournoi() + "&id=" + id + "\"" +
                "onclick=\"descriptionDone()\">Unregister</a></td></tr>";

    }
    mytournaments += "</table>";
    out.println(mytournaments);
    out.println("<br><div style=\"text-align:center\">");
    out.println("<a class=\"bu\" href=\"home.jsp?name="+name+"\">Back to home page </a></div>");
%>
</body>
</html>


<%--//bootstrap--%>
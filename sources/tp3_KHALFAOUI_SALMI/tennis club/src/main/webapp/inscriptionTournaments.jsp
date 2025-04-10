<%@ page import="fr.ensicaen.dp.tennis.appli.DataBase" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="fr.ensicaen.dp.tennis.entities.TournoiEntity" %>
<%@ page import="fr.ensicaen.dp.tennis.entities.InscriptionEntity" %>
<%@ page import="fr.ensicaen.dp.tennis.entities.AdherentEntity" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello World</title>
    <link rel="stylesheet" href="styles/inscriptionTournament.css">
    <script type="text/javascript" src="js/inscriptionTournament.js"></script>
</head>
<body>
<h1 style="text-align:center; color: #fefefe;"><%= "Hello champion!" + "<br> Here are some tournaments that may interest you" %></h1>
<br/>


<%
    DataBase base = new DataBase();
    String name = (String) session.getAttribute("name");
    AdherentEntity myInfo = base.getPersonalInformation(name);
    String id = myInfo.getNumeroadherent();
    ArrayList<InscriptionEntity> listTournaments = base.getInscription(id);
    ArrayList<String> codes = new ArrayList<>();
    for (int i = 0; i < listTournaments.size(); i++) {
        codes.add(listTournaments.get(i).getCodetournoi());
    }
    ArrayList<TournoiEntity> list = base.getTournamentList(codes);
    String table = "<table class=\"center\">";
    for (TournoiEntity tournament : list) {
        table += "<tr><td>" + tournament.getCodetournoi() + "</td><td>" + tournament.getNom() +
                "</td><td>" + tournament.getDate() + "</td><td>" + tournament.getLieu() +
                "</td><td><a id='bu' href=\"InscriptionServlet?code=" + tournament.getCodetournoi().replaceAll("\\s", "") + "&id=" + id + "\"" +
                "onclick=\"inscriptionDone()\">Register</a></td></tr>";
    }
    table += "</table>";

    out.println(table);
    out.println("<br><div style=\"text-align:center\">");
    out.println("<a class=\"bu\" href=\"home.jsp?name="+name+"\">Back to home page </a></div>");
%>

</body>
</html>
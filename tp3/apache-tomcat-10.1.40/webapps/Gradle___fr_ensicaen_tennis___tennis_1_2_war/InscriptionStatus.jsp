<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.ensicaen.tennis.persistence.TournoiEntity" %>
<%@ page import="java.text.SimpleDateFormat" %>
<jsp:include page="WEB-INF/includes/header.jsp">
    <jsp:param name="credentials" value="false"/>
</jsp:include>


<%
    String status = (String) request.getAttribute("status");
    boolean success = (boolean) request.getAttribute("success");
    TournoiEntity tournoi = (TournoiEntity) request.getAttribute("tournoi");

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDate = "";
    if (tournoi != null) {
        formattedDate = dateFormat.format(tournoi.getDate());
    }
%>

<div class="container-fluid m-3">
    <div class="row border my-title">
        <h1>Statut de l'Inscription</h1>
    </div>

    <div class="alert alert-info" role="alert">
        <p><strong><%= status != null ? status : "Erreur inconnue." %></strong></p>
    </div>

    <%if (tournoi != null && success) {%>
        <div class="mt-3">
            <h3>Inscription enregistrée pour le tournoi</h3>
            <p><strong>Nom du tournoi:</strong> <%= tournoi.getNom() %></p>
            <p><strong>Date:</strong> <%= formattedDate %></p>
            <p><strong>Lieu:</strong> <%= tournoi.getLieu() %></p>
        </div>
    <%}%>

    <a href="../service/inscription" class="btn btn-primary mt-3">Retour à la liste des tournois</a>
</div>

<jsp:include page="WEB-INF/includes/footer.jsp" />

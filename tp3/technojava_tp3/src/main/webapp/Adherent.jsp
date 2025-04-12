<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.ensicaen.tennis.persistence.AdherentEntity" %>
<%@ page import="fr.ensicaen.tennis.bean.AdherentBean" %>
<%@ page import="fr.ensicaen.tennis.util.TournoiInscriptionDTO" %>

<jsp:include page="WEB-INF/includes/header.jsp">
    <jsp:param name="credentials" value="false"/>
</jsp:include>

<jsp:useBean id="adherentBean" class="fr.ensicaen.tennis.bean.AdherentBean" scope="request">
    <%
        final AdherentEntity adherent = (AdherentEntity)request.getAttribute("adherent");
        final List<TournoiInscriptionDTO> tournois = adherentBean.getTournoiInfosByAdherent(adherent.getNumeroAdherent());
    %>

    <div class="container my-5">
        <div class="mb-5">
            <h2 class="mb-4">Informations personnelles</h2>
            <table class="table table-bordered table-striped">
                <tbody>
                <tr>
                    <th scope="row">Nom</th>
                    <td><%= adherent.getNom() %></td>
                </tr>
                <tr>
                    <th scope="row">Prénom</th>
                    <td><%= adherent.getPrenom() %></td>
                </tr>
                <tr>
                    <th scope="row">Adresse</th>
                    <td><%= adherent.getAdresse() %></td>
                </tr>
                <tr>
                    <th scope="row">Téléphone</th>
                    <td><%= adherent.getTelephone() %></td>
                </tr>
                <tr>
                    <th scope="row">Email</th>
                    <td><%= adherent.getEmail() %></td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="mt-5">
            <h2 class="mb-4">Tournois auxquels vous êtes inscrit(e)</h2>
            <%
                if (tournois.isEmpty()) {
            %>
            <div class="alert alert-info">Aucune inscription à un tournoi pour le moment.</div>
            <%
            } else {
            %>
            <table class="table table-hover table-bordered">
                <thead class="table-light">
                <tr>
                    <th>Code Tournoi</th>
                    <th>Nom</th>
                    <th>Lieu</th>
                    <th>Date</th>
                    <th>Date d'inscription</th>
                </tr>
                </thead>
                <tbody>
                <% for (TournoiInscriptionDTO t : tournois) { %>
                <tr>
                    <td><%= t.codeTournoi %></td>
                    <td><%= t.nomTournoi %></td>
                    <td><%= t.lieuTournoi %></td>
                    <td><%= t.dateTournoi %></td>
                    <td><%= t.dateInscription %></td>
                </tr>
                <% } %>
                </tbody>
                <caption>Vous êtes inscrit(e) à <%= tournois.size() %> tournoi(s)</caption>
            </table>
            <%
                }
            %>
        </div>
    </div>

    <jsp:include page="WEB-INF/includes/footer.jsp" />
</jsp:useBean>

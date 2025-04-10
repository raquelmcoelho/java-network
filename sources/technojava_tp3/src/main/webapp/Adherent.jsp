<%@ page import="fr.ensicaen.tennis.persistence.TournoiEntity" %>
<%@ page import="fr.ensicaen.tennis.persistence.AdherentEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: alexandre
  Date: 26/03/2025
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="WEB-INF/includes/header.jsp">
    <jsp:param name="credentials" value="false"/>
</jsp:include>

<jsp:useBean id="adherentBean" class="fr.ensicaen.tennis.bean.AdherentBean" scope="request">
    <%
        final String path = request.getContextPath();
        final AdherentEntity adherent = (AdherentEntity)request.getAttribute("adherent");
        final List<TournoiEntity> tournois = adherentBean.getListTournoiOf(adherent);
    %>

    <h2 class="my-title border ">Mon profil</h2>
<div class="container py-5">

    <div class="container mt-5">
        <div class="row">
            <!-- Fiche Adhérent -->
            <div class="col-md-6">
                <div class="card shadow-sm">
                    <div class="card-header bg-dark text-white">
                        <h4 class="mb-0">Fiche Adhérent</h4>
                    </div>
                    <div class="card-body">
                        <div class="row mb-2">
                            <div class="col-3 fw-bold">Numéro :</div>
                            <div class="col-9">${adherent.getNumeroAdherent()}</div>
                        </div>
                        <div class="row mb-2">
                            <div class="col-3 fw-bold">Nom :</div>
                            <div class="col-9">${adherent.getNom()}</div>
                        </div>
                        <div class="row mb-2">
                            <div class="col-3 fw-bold">Prénom :</div>
                            <div class="col-9">${adherent.getPrenom()}</div>
                        </div>
                        <div class="row mb-2">
                            <div class="col-3 fw-bold">Adresse :</div>
                            <div class="col-9">${adherent.getAdresse()}</div>
                        </div>
                        <div class="row mb-2">
                            <div class="col-3 fw-bold">Téléphone :</div>
                            <div class="col-9">${adherent.getTelephone()}</div>
                        </div>
                        <div class="row">
                            <div class="col-3 fw-bold">Email :</div>
                            <div class="col-9">${adherent.getEmail()}</div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Liste des Tournois sous forme de tableau -->
            <div class="col-md-6">
                <div class="card shadow-sm">
                    <div class="card-header bg-dark text-white">
                        <h4 class="mb-0">Liste des Tournois</h4>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th scope="col">Nom</th>
                                <th scope="col">Lieu</th>
                                <th scope="col">Date</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% for(TournoiEntity tournoi : tournois) { %>
                            <tr>
                                <td><%=tournoi.getNom()%></td>
                                <td><%=tournoi.getLieu()%></td>
                                <td><%=tournoi.getDate()%></td>
                            </tr>
                            <% } %>
                            </tbody>
                        </table>
                        <form method="post" action="<%=path%>/action">
                            <input type="hidden" name="code" value="I"/>
                            <button type="submit">S'inscrire à un tournoi</button>
                        </form>
                    </div>


                </div>
            </div>
        </div>
    </div>


</jsp:useBean>
<jsp:include page="WEB-INF/includes/footer.jsp" />


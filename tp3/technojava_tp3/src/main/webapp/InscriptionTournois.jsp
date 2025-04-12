<%@ page import="fr.ensicaen.tennis.persistence.TournoiEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="WEB-INF/includes/header.jsp">
    <jsp:param name="credentials" value="false"/>
</jsp:include>

<jsp:useBean id="tournoiBean" class="fr.ensicaen.tennis.bean.TournoiBean" scope="request">
    <%
        final List<TournoiEntity> tournois = tournoiBean.getTournoiList();
    %>


    <div class="container mt-5">
        <h1>Liste des Tournois</h1>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Code</th>
                <th scope="col">Nom</th>
                <th scope="col">Date</th>
                <th scope="col">Lieu</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
                <% for(TournoiEntity tournoi: tournois) { %>
                <tr>
                    <td><%=tournoi.getCodeTournoi() %></td>
                    <td><%=tournoi.getNom() %></td>
                    <td><%=new SimpleDateFormat("dd/MM/yy").format(tournoi.getDate()) %></td>
                    <td><%=tournoi.getLieu() %></td>
                    <td>
<%--                    <%if(tournoiBean.isAlreadyAdherent(tournoi.getCodeTournoi())) {%>--%>
                    <% if(true) {%>
                        <a href="service/inscription?tournoi=<%=tournoi.getCodeTournoi()%>" class="btn btn-primary">Inscription</a>
                    <% } else { %>
                        <a href="" disabled="true" class="btn btn-primary">Inscription</a>
                    <% } %>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </div>
</jsp:useBean>
<jsp:include page="WEB-INF/includes/footer.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="WEB-INF/includes/header.jsp">
    <jsp:param name="credentials" value="false"/>
</jsp:include>

<%--InscriptionTournois.jsp :--%>
<%--Affichage de la liste des tournois sous forme de table html--%>
<%--Pour chaque ligne :--%>
<%--Code – Nom – Date – Lieu + [ bouton « Inscription » ]--%>
<%--Actions : pour chaque bouton « Inscription »--%>
<%--à/action?code=I&tournoi=xxx--%>
<div class="container-fluid m-3">
    <div class="row border my-title">
        <h1>Inscription Tournois</h1>
    </div>
</div>

<jsp:include page="WEB-INF/includes/footer.jsp" />
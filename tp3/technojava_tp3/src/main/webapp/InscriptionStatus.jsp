<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="WEB-INF/includes/header.jsp">
    <jsp:param name="credentials" value="false"/>
</jsp:include>

<%--InscriptionStatus.jsp :--%>
<%--Confirmation (ou non) de l’inscription :--%>
<%--Affichage :--%>
<%--« Inscription enregistrée pour le tournoi » + Nom + « du » + Date + « à »--%>
<%--+ Lieu--%>
<div class="container-fluid m-3">
    <div class="row border my-title">
        <h1>Inscription Status</h1>
    </div>
</div>

<jsp:include page="WEB-INF/includes/footer.jsp" />
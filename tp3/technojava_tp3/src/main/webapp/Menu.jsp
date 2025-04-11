<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="WEB-INF/includes/header.jsp">
    <jsp:param name="credentials" value="false"/>
</jsp:include>


<%--Affichage du nom de l’utilisateur--%>
<%--Affichage du menu des services proposés :--%>
<%--- « Consultation de votre dossier adhérent » /action ?code=A)--%>
<%--- « Inscription à un tournoi » /action ?code=I--%>

<div class="container-fluid m-3">
    <div class="row border my-title">
        <h1>Menu</h1>
    </div>
</div>

<jsp:include page="WEB-INF/includes/footer.jsp" />
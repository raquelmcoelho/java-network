<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="WEB-INF/includes/header.jsp">
    <jsp:param name="credentials" value="false"/>
</jsp:include>

<%--A dherent.jsp :--%>
<%--Affichage des informations relatives à l’adhérent (informations personnelles,--%>
<%--liste des tournois auxquels il est inscrit)--%>
<div class="container-fluid m-3">
    <div class="row border my-title">
        <h1>Adherent</h1>
    </div>
</div>

<jsp:include page="WEB-INF/includes/footer.jsp" />
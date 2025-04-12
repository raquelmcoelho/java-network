<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.ensicaen.tennis.persistence.AdherentEntity" %>
<jsp:include page="WEB-INF/includes/header.jsp">
    <jsp:param name="credentials" value="false"/>
</jsp:include>

<%
    AdherentEntity adherent = (AdherentEntity) session.getAttribute("adherent");
%>

<div class="container my-5">
    <div class="text-center mb-5">
        <h1 class="display-4">Bonjour, <%= adherent.getPrenom() %> <%= adherent.getNom() %> !</h1>
        <p class="lead">Bienvenue sur votre espace adhérent. Que souhaitez-vous faire aujourd'hui ?</p>
    </div>

    <div class="row justify-content-center">
        <div class="col-md-5 mb-4">
            <div class="card shadow-sm border-primary">
                <div class="card-body text-center">
                    <h5 class="card-title">Consulter votre dossier</h5>
                    <p class="card-text">Accédez à toutes les informations concernant votre adhésion.</p>
                    <a href="action?code=A" class="btn btn-primary">Voir mon dossier</a>
                </div>
            </div>
        </div>

        <div class="col-md-5 mb-4">
            <div class="card shadow-sm border-success">
                <div class="card-body text-center">
                    <h5 class="card-title">Inscription à un tournoi</h5>
                    <p class="card-text">Rejoignez un tournoi et montrez vos compétences !</p>
                    <a href="action?code=I" class="btn btn-success">S'inscrire</a>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="WEB-INF/includes/footer.jsp" />

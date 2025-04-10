<%--
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

<div class="container py-5">
    <h2 class="mb-4">Profil de ${adherent.prenom} ${adherent.nom}</h2>

    <div class="row g-4">
        <!-- Colonne gauche : infos perso -->
        <div class="col-md-4">
            <div class="profile-section">
                <div class="section-title">Informations personnelles</div>
                <dl class="row mb-0">
                    <dt class="col-5">Nom :</dt>
                    <dd class="col-7">${adherent.nom}</dd>

                    <dt class="col-5">Prénom :</dt>
                    <dd class="col-7">${adherent.prenom}</dd>

                    <dt class="col-5">Email :</dt>
                    <dd class="col-7">${adherent.email}</dd>

                    <dt class="col-5">Téléphone :</dt>
                    <dd class="col-7">${adherent.telephone}</dd>

                </dl>
            </div>
        </div>
    </div>
</div>

<jsp:include page="WEB-INF/includes/footer.jsp" />


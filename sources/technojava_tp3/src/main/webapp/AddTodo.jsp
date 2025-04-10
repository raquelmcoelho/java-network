<%@ page import="fr.ensicaen.tennis.persistence.TournoiEntity" %>
<%@ page import="fr.ensicaen.tennis.persistence.AdherentEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Joan Reynaud
  Date: 26/02/2025
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="WEB-INF/includes/header.jsp">
	<jsp:param name="credentials" value="true"/>
</jsp:include>


<jsp:useBean id="adherentBean" class="fr.ensicaen.tennis.bean.AdherentBean" scope="request">
	<%
		final String path = request.getContextPath();
		final AdherentEntity adherent = (AdherentEntity)request.getSession().getAttribute("adherent");
		final List<TournoiEntity> tournois = adherentBean.getListTournoi();
	%>

<div class="container-fluid m-3">
	<div class="row border my-title">
		<h1>Ajouter un tounoi</h1>
	</div>
	<div class="row mt-3">
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
					<% List<TournoiEntity> tournoisOfAdherent = adherentBean.getListTournoiOf(adherent);
					if (!tournoisOfAdherent.contains(tournoi)) {%>
					<td>

						<form method="post" action="<%=path%>/inscription">
							<input type="hidden" name="tournoi" value="<%=tournoi.getCodeTournoi()%>">
							<button type="submit">Inscription</button>
						</form>
					</td>
					<% } %>
				</tr>
				<% } %>
				</tbody>
			</table>
		</div>
	</div>
</div>
</jsp:useBean>

<jsp:include page="WEB-INF/includes/footer.jsp" />

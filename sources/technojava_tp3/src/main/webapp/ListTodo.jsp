<%--
  Created by IntelliJ IDEA.
  User: Joan Reynaud
  Date: 26/02/2025
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.ensicaen.tennis.persistence.TodoEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.ensicaen.tennis.persistence.TournoiEntity" %>
<%@ page import="fr.ensicaen.tennis.persistence.AdherentEntity" %>
<jsp:include page="WEB-INF/includes/header.jsp">
	<jsp:param name="credentials" value="false"/>
</jsp:include>

<jsp:useBean id="adherentBean" class="fr.ensicaen.tennis.bean.AdherentBean" scope="request">
	<%
		final AdherentEntity adherent = (AdherentEntity)request.getAttribute("adherent");
		final List<TournoiEntity> tournois = adherentBean.getListTournoiOf(adherent);
	%>

	<div class="container-fluid m-3">
		<div class="row mt-3 border my-title">
			<h1>Liste des t&acirc;ches</h1>
		</div>
		<div class="row mt-3">
			<table class="table">
				<thead><tr>
					<th scope="col"></th>
					<th scope="col"></th>
					<th scope="col"></th>
					<th></th>
				</tr></thead>
<% for(TournoiEntity tournoi : tournois) { %>
				<tr>
					<td><%=tournoi.getNom()%></td>
					<td><%=tournoi.getLieu()%></td>
					<td><%=tournoi.getDate()%></td>
<%--					<td><%= new_todo != null && todo.getIdTodo() == new_todo.getIdTodo() ? "c'est la nouvelle t&acirc;che !" : ""%></td>--%>
				</tr>
<% } %>
				<caption>Il y a <%=tournois.size()%> tournois</caption>
			</table>
		</div>
	</div>
</jsp:useBean>

<jsp:include page="WEB-INF/includes/footer.jsp" />

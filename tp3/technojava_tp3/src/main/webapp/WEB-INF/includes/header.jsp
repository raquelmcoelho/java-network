<%@ page import="fr.ensicaen.tennis.ApplicationProperties" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
	final String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="fr" data-bs-theme="auto">
<head>
	<title>Tennis</title>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<link href="<%=path%>/css/myapp.css" rel="stylesheet"  >
	<script type="module" src="<%=path%>/js/color-modes.js" integrity="sha384-4VBPEsXn8OFy1QjsLh45jTyxGGLeBsKwRpHPloZEV+t6lgoIGs41R+vHsLwHRtxk" crossorigin="use-credentials"></script>
<body>
<nav class="navbar navbar-expand-lg bg-light shadow-sm p-3 mb-4 rounded">
	<div class="container-fluid">
		<a class="navbar-brand d-flex align-items-center" href="<%=path%>/">
			<img src="<%=path%>/media/tennis_ball.svg" alt="Tennis Ball" width="40" height="40" class="me-2">
			<div>
				<div class="fw-bold fs-5"><%=ApplicationProperties.get("club_name")%></div>
				<div class="small text-muted">
					<%=ApplicationProperties.get("club_address")%><br>
					Tél : <%=ApplicationProperties.get("club_number")%>
				</div>
			</div>
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarContent" aria-controls="navbarContent"
				aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse justify-content-end " id="navbarContent">
			<ul class="navbar-nav mb-2 mb-lg-0">
				<% if (session != null && session.getAttribute("adherent") != null) { %>
				<li class="nav-item m-3">
					<form action="<%=path%>/Menu.jsp" method="post" class="d-flex align-items-center">
						<button type="submit" class="btn btn-outline-primary">Retour au menu</button>
					</form>
				</li>

				<li class="nav-item m-3">
					<form action="<%=path%>/logout" method="post" class="d-flex align-items-center">
						<button type="submit" class="btn btn-outline-primary">Quitter</button>
					</form>
				</li>
				<% } %>
			</ul>
		</div>
	</div>
</nav>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="WEB-INF/includes/header.jsp">
	<jsp:param name="credentials" value="false"/>
</jsp:include>

<div class="container-fluid m-3">
	<div class="row border my-title">
		<h1>Login</h1>
	</div>

	<form class="mt-4" action="action?code=L" method="post">
		<div class="mb-3">
			<label for="email" class="form-label">Email</label>
			<input type="email" class="form-control" id="email" name="email" required>
		</div>
		<div class="mb-3">
			<label for="password" class="form-label">Mot de passe</label>
			<input type="password" class="form-control" id="password" name="password" required>
		</div>
		<button type="submit" class="btn btn-primary">Connexion</button>
	</form>

	<div class="row mt-3">
		<a href="console/">Console H2 database</a>
	</div>
</div>

<jsp:include page="WEB-INF/includes/footer.jsp"/>

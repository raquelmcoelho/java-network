<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.ensicaen.tennis.persistence.AdherentEntity" %>
<%@ page import="java.util.List" %>
<jsp:include page="WEB-INF/includes/header.jsp">
    <jsp:param name="credentials" value="false"/>
</jsp:include>

<jsp:useBean id="adherentBean" class="fr.ensicaen.tennis.bean.AdherentBean" scope="request">
    <%
        AdherentEntity adherent = (AdherentEntity)request.getSession().getAttribute("adherent");
        final String path = request.getContextPath();
    %>

<h1>Bienvenue, <%=adherent.getPrenom()%>!</h1> <!-- Assuming 'userName' is set as an attribute in the request -->

<h2>Menu des services proposés :</h2>

<div class="parent d-flex justify-content-center align-items-center ">

    <ul class="d-flex p-0">
        <li class="me-4">
            <form action="<%=path%>/action" method="post">
                <input type="hidden" name="code" value="A" />
                <button type="submit" class="btn bg-dark  custom-btn">Consultation de votre dossier adhérent <br>
                <img src="account.png" height="100px" width="100px" alt="" >
                </button>
            </form>
        </li>
        <li>
            <form action="<%=path%>/action" method="post">
                <input type="hidden" name="code" value="I" />
                <button type="submit" class="btn bg-dark  custom-btn">Inscription à un tournoi <br>
                    <img src="account.png" height="100px" width="100px" alt="" >
                </button>
            </form>
        </li>
    </ul>

</div>


</jsp:useBean>
<jsp:include page="WEB-INF/includes/footer.jsp" />

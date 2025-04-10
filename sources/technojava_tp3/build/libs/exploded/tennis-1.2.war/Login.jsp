<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="WEB-INF/includes/header.jsp">
    <jsp:param name="credentials" value="false"/>
</jsp:include>

<%
    final String path = request.getContextPath();
%>


    <div class="position-absolute top-50 start-50 translate-middle bg-dark p-4 rounded shadow">
        <h1>Tennis Club</h1>
        <br>
        <form action="<%=path%>/action" method="post">
            <input type="hidden" name="code" value="L"/>
            <label for="username"></label><input type="text" name="username" placeholder="Email" id="username" required> <br> <br>
            <label for="password"></label><input type="password" name="password" placeholder="Password" id="password" required> <br> <br>
            <button>Login</button>
        </form>
    </div>


<jsp:include page="WEB-INF/includes/footer.jsp" />


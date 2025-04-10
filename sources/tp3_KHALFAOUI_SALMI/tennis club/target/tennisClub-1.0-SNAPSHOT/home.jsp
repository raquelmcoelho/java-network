<%--
  Created by IntelliJ IDEA.
  User: khalfaoui
  Date: 22/03/2022
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="styles/home.css">
</head>
<body>

<div style="text-align:center">
    <img src="img/ensiPic.jpg" alt="Trulli" width="300" height="300">
    <h1 style="text-align:center; color: #fefefe;"><%
        String n = request.getParameter("uname");
        if (n == null) {
            n = (String) session.getAttribute("name");
        }
        String s = "Hello " + n;
        out.println(s);
    %></h1>
    <a href="InscriptionServlet?code="<%request.getParameter("uname");%> style="text-align:center"><%
        out.println("Tournaments");
    %></a>
    <br>
    <a href="AdherentServlet" style="text-align:center"><%
        out.println("Personal Info");
    %></a>
</div>
</body>
</html>

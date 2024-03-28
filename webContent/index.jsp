
<%
	if(session.getAttribute("user") == null){
		response.sendRedirect("login.jsp");	}
	else
		response.sendRedirect("index.jsp");
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>hiiii</h1>
	<a href="public/1_temp.jsp">mylink</a>
</body>
</html>
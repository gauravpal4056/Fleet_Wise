<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<script>
window.location.reload(true);
</script>
<%
    // Invalidate the session
    session.invalidate();
	response.sendRedirect("login.jsp");
%>


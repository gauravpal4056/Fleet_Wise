  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%
	response.setHeader("Cache-Control", "no-cache, no-store,must-revalidate");
	
	
	response.setHeader("Cache-Control", "no-cache");
    	response.setHeader("Cache-Control", "no-store");
    	response.setHeader("Pragma", "no-cache");
    	response.setDateHeader("Expires", 0);
    	
    	


        response.sendRedirect("login.jsp"); 

        session.invalidate();
        %>
<script>
window.location.reload(true);
</script>



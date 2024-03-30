<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="model.Route" %>
<%@page import="model.Vehicle" %>
<%@page import="model.Hub" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>

<style>
	@charset "UTF-8";
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  
  body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    font-size: 14px;

  }
 .form-flex{
    display: flex;
    justify-content: space-between;
    /* background-color: #0056b3; */
 }
 
 input, select {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }

 
 .body-form{
    height: 100vh;
    /* display: flex; */
    padding-top: 60px;
   
    /* align-items: center; */
 }
  
 .oc1{
    width: 950px;
 }
  .lp{
    padding-bottom: 5px;
  }
  .container {
   
    max-width: 800px;
    margin: 50px auto;
    padding: 40px;

    background-color: #fff;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }

  
  
  h2 {
    text-align: center;
    margin-bottom: 20px;
  }
  
  .form-group {
    margin-bottom: 20px;
    width: 45%;
  }
  
  label {
    display: block;
    font-weight: bold;
  }
  
  input {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    /* border-radius: 5px; */
  }
  
  button {
    width: 100%;
    padding: 10px;
    background-color:black;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  button:hover {
    /* background-color: #cbd0d5;
     */
    background-color: rgb(105, 106, 106);
  }
  
  #error-message {
    color: red;
    font-size: 0.9rem;
    margin-top: 5px;
  }
  
  .hp{
    padding-bottom: 30px;
    /* border-bottom: 1px solid black; */
  }
  .bbp{
    margin-top: 10px;
  }

  @media (max-width: 768px) {

    .form-flex{
      display: block;
     
    }

    .form-group {
      margin-bottom: 20px;
      width: 100%;
    }
   
    }


    /* 
    vehicle  */

    .vehicle-form{
      width: 80%;
      /* background-color: red; */
    }

    .vehicleForm{
      /* background-color: aqua; */
      width: 80%;
    }
    .vehicle-form-con{
      width: 100%;
      display: flex;
      justify-content: center;
    }
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="vehicle-form-con">
                                <div class="body-form vehicle-form">
                                <h1 style="margin-bottom:30px;">Update Route Details</h1>
                                    <form id="routesForm" action="UpdateRouteServlet" method="post">
                                    
                                    <%
                                      // Retrieve driver object from request attribute
                                      Route route = (Route) request.getAttribute("Routes");
            
                                      // Check if driver object is not null
                                      if (route != null) {
                                    %>
                                    
                                        <div class="form-flex">
                                         <input type="hidden" id="Route_Id" name="Route_Id" value="<%= route.getRouteId() %>">
                                            <div class="form-group">
                                                <label for="routeName" class="lp">Route Name</label>
                                                <input type="text" id="Route_Name" name="Route_Name" value="<%= route.getRouteName() %>" required placeholder="Route Name">
                                            </div>
                                            <div class="form-group">
                                                <label for="startingPoint" class="lp">Starting Point</label>
                                                <input type="text" id="Start_Point" name="Start_Point" value="<%= route.getStartPoint() %>" required placeholder="Starting Point">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="destinationPoint" class="lp">Destination Point</label>
                                            <input type="text" id="Destination_Point" name="Destination_Point" value="<%= route.getDestinationPoint() %>" required placeholder="Destination Point">
                                        </div>
                                        <button type="submit" id="updateButton" name="action" value="Update" >Update Details</button>
                                        <% } %>
                                    </form>
                                    <div id="message"></div>
                                </div></div>
  <script src="add-driver.js"></script>
</body>
</html>
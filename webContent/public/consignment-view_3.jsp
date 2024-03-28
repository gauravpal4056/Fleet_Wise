<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
 <%
	LocalDate now = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd");
    String formattedDate = now.format(formatter);
%>
  
<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<%@page import="dao.ConsignmentDao" %>
<%@page import="model.Consignment" %>
<%@page import="java.util.*" %>
<%@page import="dao.HubDao" %>
<%@page import="model.Hub" %>
<%@page import="model.Vehicle" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@page import = "java.util.*" %>
<%@page import="dao.HubDao" %>
<%@ page import="model.Route" %>
<%@ page import="dao.RouteDao" %>
<%@ page import="utils.DBConnection" %>
<%@ page import="utils.DateHandler" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">


    <title>Dashboard</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->

    <script>
        window.onload = function () {
            openCity(event, 'London');
            var i, tabcontent, tablinks;
          tabcontent = document.getElementsByClassName("tabcontent");
            tablinks = document.getElementsByClassName("tablinks");
          for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
          }
          document.getElementById(cityName).style.display = "block";
          evt.currentTarget.className += " active";

        }
        function openCity(evt, cityName) {
          var i, tabcontent, tablinks;
          tabcontent = document.getElementsByClassName("tabcontent");
          for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
          }
          tablinks = document.getElementsByClassName("tablinks");
          for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
          }
          document.getElementById(cityName).style.display = "block";
          evt.currentTarget.className += " active";
        }
        </script>

<style>
    body {font-family: Arial;}
    .hpad{
        /* padding: 17px 21.25px; */
        padding-top: 25px;
        padding-left: 21.25px;
    }
    .flex-it{
        width: 100%;
        display: flex;
        justify-content: space-between;
    }
    
    /* Style the tab */
    .tab {
      overflow: hidden;
      border: 1px solid #ccc;
      background-color: #f1f1f1;
    }
    
    /* Style the buttons inside the tab */
    .tab button {
      background-color: inherit;
      float: left;
      border: none;
      outline: none;
      cursor: pointer;
      padding: 14px 16px;
      transition: 0.3s;
      font-size: 17px;
    }
    
    /* Change background color of buttons on hover */
    .tab button:hover {
      background-color: #ddd;
    }
    
    /* Create an active/current tablink class */
    .tab button.active {
      background-color: #ccc;
    }
    
    /* Style the tab content */
    .tabcontent {
      display: none;
      padding: 6px 12px;
      border: 1px solid #ccc;
      border-top: none;
    }
    </style>
    <link href="css/sb-admin-2.min.css" rel="stylesheet">


    
    <script>
        function toggleDropdown() {
          var dropdownContent = document.getElementById("myDropdown");
          dropdownContent.classList.toggle("show");
        }
        </script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />

<link href="css/own.css" rel="stylesheet">

<style>
    /* Basic styling */
    .dropdown {
      position: relative;
      display: inline-block;
      margin: 25px 20px;
      
  
    }
   
    /* .dropdown button{
        background-color: white;
        width: 15%;
        border: none;
        box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
        /* color: white; */
    /* padding: 6px 0;
    text-align: middle;

    }  */
  
  
    /* .dropdown-content {
      display: none;
      position: absolute;
      background-color: #f9f9f9;
      min-width: 160px;
      box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
      z-index: 1;
    } */
  
    .dropdown-content a {
      color: black;
      padding: 12px 16px;
      text-decoration: none;
      display: block;
    }
  
    .dropdown:hover .dropdown-content {
      display: block;
    }
  
    /* For mobile devices */
    @media screen and (max-width: 600px) {
      .dropdown:hover .dropdown-content {
        display: none;
      }
  
      .dropdown-content {
        display: block;
      }
    }
    </style>


</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gray-900 sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
                <div class="sidebar-brand-icon ">
                	<img class="img-fluid px-1 px-sm-1 mt-1 mb-1" style="width: 2.5rem;" src="img/logo.png" alt="...">
                    <!--  <i class="fas fa-laugh-wink"></i>-->
                </div>
                <div class="sidebar-brand-text mx-3">Fleet Wise<sup></sup></div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="index.jsp">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Interface
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed " href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <span style="font-size: 16px; font-weight: bold" class="material-symbols-outlined">
                        inventory_2
                        </span>
                    <span style="font-weight: bold">Consignment</span>
                </a>
                <div  id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="Consignment-view.jsp">View All Consignment</a>
                        <a class="collapse-item" href="consignment-add.jsp">Add Consignment</a>
                        <a class="collapse-item" href="consignment-delete.jsp">Delete Consignment </a> 
                    </div>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities1"
                    aria-expanded="true" aria-controls="collapseUtilities1">
                    <span class="material-symbols-outlined">
                        route
                        </span>
                    <span style="font-weight: bold">Routes</span>
                </a>
                <div id="collapseUtilities1" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="route-view.jsp">View All Route</a>
                        <a class="collapse-item" href="route-add">Add Route</a>
                        <a class="collapse-item" href="route-delete">Delete Route</a>
                    </div>
                </div>
            </li>
            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                   <span class="sizeee"> <span class="material-symbols-outlined">
                    local_shipping
                    </span></span>
                    <span style="font-weight: bold">Vehicle</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="vehicle-view.jsp">Add Vehicle</a>
                        <a class="collapse-item" href="vehicle-add.jsp">Add Vehicle</a>
                        <a class="collapse-item" href="vehicle-changeDriver.jsp">Change Driver</a>
                        <a class="collapse-item" href="vehicle-remove">Remove Vehicle</a>
                    </div>
                </div>
            </li>

            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities2"
                    aria-expanded="true" aria-controls="collapseUtilities2">
                    <span class="material-symbols-outlined">
                        id_card
                        </span>
                    <span style="font-weight: bold">Driver</span>
                </a>
                <div id="collapseUtilities2" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Driver Detail</h6>
                        <a class="collapse-item" href="driver-view.jsp">View All Driver</a>
                        <a class="collapse-item" href="driver-add.jsp">Add Driver</a>
                        <a class="collapse-item" href="driver-delete.jsp">delete Driver</a>
                    </div>
                </div>
            </li>

            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities4"
                    aria-expanded="true" aria-controls="collapseUtilities4">
                    <span class="material-symbols-outlined sizeee">
                        airline_stops
                        </span>
                    <span style="font-weight: bold">Trip</span>
                </a>
                <div id="collapseUtilities4" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="trip-view.jsp">View All Trip</a>
                    </div>
                </div>
            </li>

            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities3"
                    aria-expanded="true" aria-controls="collapseUtilities3">
                    <span class="material-symbols-outlined">
                        admin_panel_settings
                        </span>
                    <span style="font-weight: bold">Admin</span>
                </a>
                <div id="collapseUtilities3" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="admin-add.jsp">Add Admin</a>
                        <a class="collapse-item" href="admin-delete.jsp">Delete Admin</a>
                    </div>
                </div>
            </li>
        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar my-2 ">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Search -->
                    <h1 style="font-weight: 900" class="text-gray-700">CONSIGNMENTS</h1>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!-- Nav Item - Alerts -->
                        <li class="nav-item dropdown no-arrow mx-1">


                        <!-- Nav Item - Messages -->
                        
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
	                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
	                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Admin</span>
	                                <img class="img-profile rounded-circle"
	                                    src="img/undraw_profile.svg">
	                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>
                        
                        <div style="justify-content: center; align-items: center" class=" d-flex ">
                        	<a style="border-radius:20px;" class="dropdown-item text-gray-200 bg-gradient-danger" href="#" data-toggle="modal" data-target="#logoutModal">
	                           <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
	                           Logout
	                       </a>
                        </div>
                        <li class="nav-item dropdown no-arrow mx-1">

                            <!-- <span> Date | Time</span> -->
                            <a class="nav-link btn dropdown-toggle " href="#" id="messagesDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <h6 style="font-weight: 700 ;margin:0; border-radius:20px; "class="bg-gray-200 text-gray-600 border border-dark py-2 px-3  " > <%= formattedDate  %></h6>
                                <!-- Counter - Messages -->
                            </a>
                        </li>
                    </ul>
                    

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid mt-9">
                	 <div class="text-center">
                         <img class="img-fluid px-1 px-sm-1 mt-1 mb-1" style="width: 15rem;" src="img/admin.jpg" alt="...">
                     </div>
                     <h4 style="margin-bottom:20px;" class="m-9 font-weight-bold ">View consignment</h4	>
                    <!-- Page Heading -->
                 

                    <!-- Content Row -->
                    <div class="row">
                          <div class="tab-table">
                            <form class="mb-9" action="../ConsignmentServlet" method="GET">
                                <div class="form-group">
                                    <select class="btn bg-gray-200 border-primary shadow border-left-primary" id="route" name="route" required>
                                    	<option value="" disabled selected hidden>No route selected</option>
                                        <% 
                                        DBConnection dbConnection = null;
                                        Connection connection = null;
                                        try {
                                            dbConnection = DBConnection.getDbConnnection(); // Get instance of DBConnection
                                            connection = dbConnection.getConnection(); // Get connection
                                            RouteDao rDao = new RouteDao(dbConnection);
                                            List<Route> routes = rDao.findAll(); // Assuming there's a method findAllRoutes() in your DAO
                                            for (Route r : routes) {
                                    %>
                                        <option value="<%= r.getRouteId() %>"><%= r.getRouteName() %></option>
                                    <%
                                            }
                                        } catch (ClassNotFoundException | 	SQLException e) {
                                            e.printStackTrace();
                                        } 
                                    %>
                                    </select>
                                </div>
                                <button style="margin-bottom:20px;" class="btn btn-primary bg-gray-900 m-9" type="submit">   Select Route  </button>
                            </form>
<!-- <p>Click on the buttons inside the tabbed menu:</p> -->

<div class="tab m-9">
  <button class="tablinks font-weight-bold text-primary" onclick="openCity(event, 'London')">View All</button>
  <button class="tablinks font-weight-bold text-primary" onclick="openCity(event, 'Paris')">Pending</button>
  <button class="tablinks font-weight-bold text-primary" onclick="openCity(event, 'Toky')">Delivered</button>

  <button class="tablinks font-weight-bold text-primary"  onclick="openCity(event, 'Tokyo')">Canceled</button>
</div>

	<h4 class="my-3"><%=session.getAttribute("selectedRoute") %> <%=session.getAttribute("selectedRouteName") %></h4>

<div id="Toky" class="tabcontent">
    <div class="card  mb-4">
        <div class="card-header py-3">  
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>S. No</th>
                            <th>Name</th>
                            <th>HUB</th>
                            <th>DATE</th>
                            <th>Delivered</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                        <% 
			                List<Consignment> cs = (List<Consignment>) session.getAttribute("filteredConsignments"); // Assuming there's a method findAllRoutes() in your DAO
			                if (cs != null && cs.size()!=0){
			                for (Consignment c: cs) {
					          if ("delivered".equalsIgnoreCase(c.getStatus())) {// Check if status is "pending"

				        %> 
				        	<tr>
                                <td><%= c.getConsignmentId()%></td>
                                <td><%= c.getConsignmentName() %></td>
                                <td><%= c.getHub().getHubName() %></td>
                                <td><%= DateHandler.javaToStr(c.getConsignmentDate()) %></td>
                                <td><%=c.getStatus() %></td>
                            </tr>
				        <%
				            }}} else {
				            	%><h6 class="text-danger">no delivered consignments !</h3><%
				            }
				        %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div id="London" class="tabcontent">
    <div class="card  mb-4">

        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>S. No</th>
                            <th>NAME</th>
                            <th>HUB</th>
                            <th>DATE</th>
                            <th>STATUS</th>
                        
                        </tr>
                    </thead>
                    <tbody><!-- all consignments -->
                        <% 
			                if (cs != null && cs.size()!=0){
			                for (Consignment c: cs) {
					        %> 
				        	<tr>
                                  <td><%= c.getConsignmentId()%></td>
                                  <td><%= c.getConsignmentName() %></td>
                                  <td><%= c.getHub().getHubName() %></td>
                                  <td><%= DateHandler.javaToStr(c.getConsignmentDate()) %></td>
                                  <% String status=c.getStatus();
                                  if(status==null){%>
                                	  		<td class="text-primary" >Pending</td>
                                	  		<%} else  {%>
                                	  		<td ><%=status %><%} %></td>
                            </tr>
					        <%
					            }} else {
					            	%><h5 class="text-danger">no cons found !</h3><%
					            }
					        %>                     
		        	 </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div id="Paris" class="tabcontent">
    <div class="card  mb-4">
        <div class="card-header py-3 flex-it">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bd-example-modal-lg">Schedule All</button>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>NUMBER</th>
                            <th>NAME</th>
                            <th>HUB</th>
                            <th>DATE</th>
                            <th>Schedule

                                <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                                  <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                   <h3 class="hpad">   List Of Vehicle</h3>
                                      <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th>Vehicle id</th>
                                                        <th>Reg. No</th>
                                                        <th>Model</th>
                                                        <th>Fuel type</th>
                                                        <th>Maintenance Due</th>
														<th> </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                
											        <% 
								                	List<Vehicle> vs = (List<Vehicle>) session.getAttribute("vehicles"); // Assuming there's a method findAllRoutes() in your DAO
									                if (vs != null && vs.size()!=0){
									                for (Vehicle v: vs) {
											             // Check if status is "pending"
											        %> 
											        	<tr>
							                                <td><%= v.getVehicleId()%></td>
							                                <td><%= v.getRegistrationNo() %></td>
							                                <td><%= v.getModel() %></td>
							                                <td><%= v.getFuelType()%></td>
							                                <td><%= v.getMaintenanceDue() %></td>
							                                <% if(v.getStatus().equals("ACTIVE")){%>
								                       	  		<td class="text-danger" >Busy</td>
								                       	  		<%} else  {%>
								                       	  		<td ><button value="<%= v.getVehicleId()%>" onclick="ajaxPost()" type="button" class="btn btn-success" data-toggle="modal" data-target=".bd-example-modal-lg">Dispatch</button></td>
								                       	  </tr>
								                       	  		
											        <%
											            
												           }			                
									                		}}else {
										            		%><h6 class="text-danger">No Vehicle found   !</h6><%
											            }
											        %>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                            </th>
                        </tr>
                    </thead>
                    <tbody><!-- pending -->
                    	<% 
		                if (cs != null && cs.size()!=0){
		                for (Consignment c: cs) {
		                	if(c.getStatus()=="")
				        %> 
			        	<tr>
                                <td><%= c.getConsignmentId()%></td>
                                <td><%= c.getConsignmentName() %></td>
                                <td><%= c.getHub().getHubName() %></td>
                                <td><%= DateHandler.javaToStr(c.getConsignmentDate()) %></td>
                                <% String status=c.getStatus();
                                if(status==null){%>
                       	  		<td class="text-danger" >Pending</td>
                       	  		<%} else  {%>
                       	  		<td ><button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bd-example-modal-lg">Schedule All</button></td>
                          </tr>
					        <%
				            }}} else {
				            	%><h5 class="text-danger">no Pending consignment </h3><%
				            }
				        %> 
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div id="Tokyo" class="tabcontent">
    <div class="card  mb-4">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>NUMBER</th>
                            <th>NAME</th>
                            <th>HUB</th>
                            <th>DATE</th>
                            <th>STATUS</th>
                        </tr>
                    </thead>
                    <tbody><!-- cancelled -->
                        <% 
				          if (cs != null && cs.size()!=0){
				          	for (Consignment c: cs) {
				              if ("cancelled".equalsIgnoreCase(c.getStatus())) { // Check if status is "pending"
				   		%> 
				   	<tr>
                         <td><%= c.getConsignmentId()%></td>
                         <td><%= c.getConsignmentName() %></td>
                         <td><%= c.getHub().getHubName() %></td>
                         <td><%= DateHandler.javaToStr(c.getConsignmentDate()) %></td>
                         <td><%=c.getStatus() %></td>
                     </tr>
				   <%
				       }
				          }
				          	}
				               else {
				       	%><h6 class="text-danger">no cancelled consignments !</h6><%
				       }
				   %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
                          </div>

                        <div class="container-fluid">
                        </div>
                    </div>
                    </div>
            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2021</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>
    <div class="w3-container">
  

  <div id="id01" class="w3-modal">	
    <div class="w3-modal-content">
      <div class="w3-container">
        <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-display-topright">&times;</span>
        <p class="text-success text-lg">Successfully scheduled</p>
      </div>
    </div>
  </div>
</div>
    <script>
    	
	    function ajaxPost() {
	    	  // Get the div that was clicked
	    	  var div = event.target;
	    	  // Get the data from the div
	    	  var vehicle = div.value;
				console.log(div.value);

	
	    	  // Make an AJAX POST request
	    	  $.ajax({
	    	    url: "../ScheduleServlet",
	    	    type: "POST",
	    	    data: { vehicle: vehicle },
	    	    success: function(response) {
	    	    	document.getElementById('id01').style.display='block'
	    	      // Do something with the response
	    	    }
	    	  });
	    	}
    </script>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script> 

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/chart-area-demo.js"></script>
    <script src="js/demo/chart-pie-demo.js"></script>

</body>

</html>
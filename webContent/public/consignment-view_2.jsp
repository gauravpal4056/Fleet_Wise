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
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@page import = "java.util.*" %>
<%@page import="dao.HubDao" %>
<%@ page import="model.Route" %>
<%@ page import="dao.RouteDao" %>
<%@ page import="utils.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Fleet-Dashboard</title>
    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <script>
        function toggleDropdown() {
          var dropdownContent = document.getElementById("myDropdown");
          dropdownContent.classList.toggle("show");
        }
    </script>
	<link href="css/own.css" rel="stylesheet">
	<style>
    /* Basic styling */
    .dropdown {
      position: relative;
      display: inline-block;
      margin: 25px 20px;
      
  
    }
  
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
                    <h1 style="font-weight: 900" class="text-gray-700">DASHBOARD</h1>

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
                                <!-- <span class="badge badge-danger badge-counter">7</span> -->
                            </a>
                        </li>
                    </ul>
                    

                </nav>
               

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                 

                    <!-- Content Row -->
                    <div class="row">

                        <!-- <div class="dropdown">
                            <button class="dropbtn"> Select Route <span class="material-symbols-outlined paro">
                                expand_more
                                </span></button>
                            <div class="dropdown-content">
                              <a href="#">Link 1</a>
                              <a href="#">Link 2</a>
                              <a href="#">Link 3</a>
                            </div>
                          </div> -->

                          <div class="tab-table">
                            <h2>Consignment's Detail</h2>
                            <form action="../ConsignmentServlet" method="GET">
                                <div class="form-group">
                                    <select class="" id="route" name="route">
                                        <% 
                                        DBConnection dbConnection = null;
                                        Connection connection = null;
                                        try {
                                            dbConnection = DBConnection.getDbConnnection(); // Get instance of DBConnection
                                            connection = dbConnection.getConnection(); // Get connection
                                            RouteDao rDao = new RouteDao(dbConnection);
                                            List<Route> routes = rDao.findAll(); // Assuming there's a method findAllRoutes() in your DAO
                                            for (Route r : routes) {
                                                System.out.println(r.getRouteName());
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
                                <button class="btn btn-primary bg-gray-900" type="submit">Select Route</button>
                            </form>
<!-- <p>Click on the buttons inside the tabbed menu:</p> -->

<div class="tab">
  <button class="tablinks" onclick="openCity(event, 'London')">View All</button>
  <button class="tablinks" onclick="openCity(event, 'Paris')">Pending</button>
  <button class="tablinks" onclick="openCity(event, 'Toky')">Dileverd</button>
  <button class="tablinks" onclick="openCity(event, 'Tokyo')">Canceled</button>
</div>


<div id="Toky" class="tabcontent">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
          <div>  <h6 class="m-0 font-weight-bold text-primary">Dileverd</h6></div>       
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Nhell</th>
                            <th>NAjhdvidwgcME</th>
                            <th>HUB</th>
                            <th>DATE</th>
                            <th>STATUS</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
		                List<Consignment> cs = (List<Consignment>) session.getAttribute("filteredConsignments"); // Assuming there's a method findAllRoutes() in your DAO
		                if (cs != null && cs.size()!=0){
		                for (Consignment c: cs) {
		                	System.out.println(c.getConsignmentName());
			        	%> 
			        	<tr>
                            <td><%= c.getConsignmentId()%></td>
                            <td><%= c.getConsignmentName() %></td>
                            <td><%= c.getHub().getHubName() %></td>
                            <td><%=c.getConsignmentDate() %></td>
                            <td><%=c.getStatus() %></td>
                        </tr>
				        <%
				            }} else {
				            	%><h3 class="text-danger">No consignments found !</h3><%
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
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary"> All Consignment</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Nhell</th>
                            <th>NAME</th>
                            <th>HUB</th>
                            <th>DATE</th>
                            <th>STATUS</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Tiger Nixon</td>
                            <td>Edinburgh</td>
                            <td>2011/04/25</td>
                            <td>Active</td>
                        </tr>
                        <tr>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div id="Paris" class="tabcontent">
    <div class="card shadow mb-4">
        <div class="card-header py-3 flex-it">
            <h6 class="m-0 font-weight-bold text-primary">Consignment's</h6>
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
                                                        <th>Model No</th>
                                                        <th>Reg. No</th>
                                                        <th>F-Type</th>
                                                        <th>DATE</th>
                                                        <th>Next Maintain</th>
                                                        <th>Driver </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>1</td>
                                                        <td>Tiger </td> 
                                                        <td>Petrol</td>
                                                        <td>2011/04/25</td>
                                                        <td>Active</td>
                                                        <td>
                                                           <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bd-example-modal-lg">Schedule All</button>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-primary">Save changes</button>
                                    </div>
                                  </div>
                                </div>
                              </div>
                             </th>
                            </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Tiger Nixon</td>
                            <td>Edinburgh</td>
                            <td>2011/04/25</td>
                            <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bd-example-modal-lg">Schedule</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div id="Tokyo" class="tabcontent">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Canceled</h6>
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
                            <th>STATUS</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Tiger Nixon</td>
                            <td>Edinburgh</td>
                            <td>2011/04/25</td>
                            <td>Active</td>
                        </tr>
                        <tr>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</div>
                        
</div>
</div>
</div>
            <!-- End of Main Content -->
				
				

                    <!-- Content Row -->
                    
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2024	</span>
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
                    <a class="btn bg-gradient-danger text-gray-100" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

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

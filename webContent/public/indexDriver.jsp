<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	System.out.println(session.getAttribute("user"));
	if(session.getAttribute("user")==null || session.getAttribute("user").equals("admin")){
		response.sendRedirect("login.jsp");	}
%>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
 <%
	LocalDate now = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd");
    String formattedDate = now.format(formatter);
%>
<%@page import="model.Driver" %>
<%@page import="model.Vehicle" %>
<%@page import="model.Route" %>
<%@page import="model.Trip" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
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

    <style>
    
     
    </style>


    
    <script>
        function toggleDropdown() {
          var dropdownContent = document.getElementById("myDropdown");
          dropdownContent.classList.toggle("show");
        }
        </script>

<link href="css/own.css" rel="stylesheet">


</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gray-900 sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
             <a class="sidebar-brand d-flex align-items-center justify-content-center" href="DriverDashboardServlet">
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
                <a class="nav-link" href="../DriverDashboardServlet">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Driver</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Options
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="public/driver-consignment.jsp" 
                   >
                    <span class="material-symbols-outlined">
                        inventory_2
                        </span>
                    <span>Consingments</span>
                </a>
                
            </li>


            
            
         
            <li class="nav-item">
                <a class="nav-link collapsed" href="public/driver-trip.jsp" 
                   >
                    <span class="material-symbols-outlined">
                       route
                        </span>
                    <span>Trip</span>
                </a>
                
            </li>


            


            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities10"
                    aria-expanded="true" aria-controls="collapseUtilities10">
                    <span class="material-symbols-outlined">
                        route
                        </span>
                    <span>Issue</span>
                </a>
                <div id="collapseUtilities10" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="driver-issue-view.jsp">view all issues</a>
                        <a class="collapse-item" href="driver-issue-veh-create.jsp">raise vehicle issue</a>
                        <a class="collapse-item" href="driver-issue-con-create.jsp">raise consignment issue</a>
                        <!-- <a class="collapse-item" href="utilities-other.html">Other</a> -->
                    </div>
                </div>
            </li>
            <!-- Nav Item - Utilities Collapse Menu -->
       

     


            <!-- Divider -->
            <!-- <hr class="sidebar-divider"> -->

          

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column ">

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
	                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Driver</span>
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
                <!-- End of Top bar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->

                    <div class="text-center">
                         <img class="img-fluid px-1 px-sm-1 mt-1 mb-1" style="width: 14rem;" src="img/driver.jpg" alt="...">
                     </div>
						<h4 style="font-weight: 600; margin-bottom: 30px;" class="font-bold">Today's Agenda</h4>

                    <!-- Content Row -->

                    
                    <div class="row">
                     	<div class="card" style="width: 16rem;">
                     	<h5 class="card-title">My Profile</h5>
						  <img style="height:150px;"  src="https://cdn.dribbble.com/users/1176657/screenshots/15468294/media/34af996ddff444391edab94abcf3c7f3.png" class="card-img-top" alt="...">
						  <div class="card-body">
						    <%Driver d = (Driver) session.getAttribute("driver"); 
                            	if(d!=null)	{
                            %>
                              <h5 style="font-weight: bolder;" class="t-t">Hii <%= d.getName()%></h5>
                              <h6 class="text-body">Phone: <%=d.getPhoneNumber() %></h6>
                              <h6 class="text-body">E-mail: <%= d.getEmailAddress()%></h6 >
                              <h6 class="text-body">Status: <%= d.getAvailable()%></h6>
                              <%} else{%>  
                              <h1>error occured refresh!</h1>
                              <%} %>
						  </div>
						</div>
						<div class="card" style="width: 16rem;">
							<h5 class="card-title">My Vehicle</h5>
						  <img style="height:200px;" src="img/vehicle.jpg" class="card-img-top" alt="...">

						  <div class="card-body">
						    <%Vehicle v = (Vehicle) request.getAttribute("vehicle"); 
                            	if(v!=null)	{
                            %>
                              <h5 style="font-weight: bolder;" class="t-t">Hii <%= v.getModel()%></h5>
                              <h6 class="text-body">Phone: <%=d.getPhoneNumber() %></h6>
                              <h6 class="text-body">E-mail: <%= d.getEmailAddress()%></h6 >
                              <h6 class="text-body">Status: <%= d.getAvailable()%></h6>
                              <%} else{%>  
                              <h1>error occured refresh!</h1>
                              <%} %>
					
						  </div>
						</div>
						<div class="card" style="width: 18rem;">
							<h5 class="card-title">My Profile</h5>
						  	<img style="height:200px;" src="img/vehicle.jpg" class="card-img-top" alt="...">						  <div class="card-body">
						    <h5 class="card-title">Card title</h5>
						    <p class="card-text">lk of the card's content.</p>
						    <a href="#" class="btn btn-primary">Go somewhere</a>
						  </div>
						</div>
						<div class="card" style="width: 18rem;">
							<h5 class="card-title">My Profile</h5>
						  	<img style="height:200px;" src="img/vehicle.jpg" class="card-img-top" alt="...">						  
						  	<div class="card-body">
						    <h5 class="card-title">Card title</h5>
						    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
							<p  class="dbb text-body" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                              aria-expanded="false">Hubs Connected  <i class="fa fa-caret-down" aria-hidden="true"></i></p>
                              <div class="dropdown-menu border" aria-labelledby="dropdownMenuButton">
                                  <a class="dropdown-item" href="#">Hub 1</a>
                                  <a class="dropdown-item" href="#">hub2</a>
                                  <a class="dropdown-item" href="#">Hub3</a>
                              </div>
						  </div>						  
						  </div>
						</div>
                    </div>
            </div>
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
                    <a class="btn bg-gradient-danger text-gray-100" href="logout.jsp">Logout</a>
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
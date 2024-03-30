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
<%@page import="model.Consignment" %>
<%@page import="model.Vehicle" %>
<%@page import="model.NextHub" %>
<%@page import="model.Route" %>
<%@page import="model.Hub" %>
<%@page import="model.Trip" %>
<%@ page import="utils.DateHandler" %>

<%@page import="java.util.*" %>


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
    
    	::-webkit-scrollbar {
		  width: 4px;
		}
		
		/* Track */
		::-webkit-scrollbar-track {
		  background: #f1f1f1; 
		}
		 
		/* Handle */
		::-webkit-scrollbar-thumb {
		  background: #888; 
		}
		
		/* Handle on hover */
		::-webkit-scrollbar-thumb:hover {
		  background: #555; 
		}
		/* Timeline Container */
.timeline-t {
  background: var(--primary-color);
  margin: 20px auto;
  padding: 20px;
}

/* Card container */
.card-t {
  position: relative;
  max-width: 400px;
}
.timeline-container{
    width: 100%;
    /* background-color: red; */
    display: flex;
    justify-content: space-between
}

/* setting padding based on even or odd */
.card-t:nth-child(odd) {
  padding: 30px 0 30px 30px;
}
.card-t:nth-child(even) {
  padding: 30px 30px 30px 0;
}
/* Global ::before */
.card-t::before {
  content: "";
  position: absolute;
  width: 50%;
  border: solid #9f9999;
}

/* Setting the border of top, bottom, left */
.card-t:nth-child(odd)::before {
  left: 0px;
  top: -4.5px;
  bottom: -4.5px;
  border-width: 5px 0 5px 5px;
  border-radius: 50px 0 0 50px;
}
.t-f-c{
    width: 100%;
    display: flex;
    justify-content: space-around;
}

/* Setting the top and bottom to "-5px" because earlier it was out of a pixel in mobile devices */
@media only screen and (max-width: 700px) {

    .footer-left-timeline, .footer-middle-timeline, .footer-right-timeline{
        width: 100%;
        display: flex;
        justify-content: center;
    }
   .t-f-c{
    display: block;
    /* display: flex; */
    /* justify-content: center; */
   }
    .timeline-footer{
        /* width: 100%;
        display: flex;
        justify-content: space-around; */
        padding-left: 0px;
    }
  .card-t:nth-child(odd)::before {
    top: -5px;
    bottom: -5px;
  }
  .timeline-container{
    width: 100%;
    /* background-color: red; */
    display: block;
}
}

/* Setting the border of top, bottom, right */
.card-t:nth-child(even)::before {
  right: 0;
  top: 0;
  bottom: 0;
  border-width: 5px 5px 5px 0;
  border-radius: 0 50px 50px 0;
}

/* Removing the border if it is the first card */
.card-t:first-child::before {
  border-top: 0;
  border-top-left-radius: 0;
}

/* Removing the border if it is the last card  and it's odd */
.card-t:last-child:nth-child(odd)::before {
  border-bottom: 0;
  border-bottom-left-radius: 0;
}

/* Removing the border if it is the last card  and it's even */
.card-t:last-child:nth-child(even)::before {
  border-bottom: 0;
  border-bottom-right-radius: 0;
}

/* Information about the timeline */
.info-t {
  display: flex;
  flex-direction: column;
  background: #333;
  color: gray;
  border-radius: 10px;
  padding: 10px;
}

/* Title of the card */
.title-t {
  color: white;
  position: relative;
}

/* Timeline dot  */
.title-t::before {
  content: "";
  position: absolute;
  width: 20px;
  height: 20px;
  background: white;
  border-radius: 50%;
  border: 3px solid #333;
}

/* text right if the card is even  */
.card-t:nth-child(even) > .info-t > .title-t {
  text-align: right;
}

/* setting dot to the left if the card is odd */
.card-t:nth-child(odd) > .info-t > .title-t::before {
  left: -45px;
}

/* setting dot to the right if the card is odd */
.card-t:nth-child(even) > .info-t > .title-t::before {
  right: -45px;
}

    </style>

<style>
    table {
      font-family: arial, sans-serif;
      border-collapse: collapse;
      width: 100%;
     /* margin-right: 1px; */
    }
    
    td, th {
      border: 1px solid #dddddd;
      text-align: left;
      padding: 8px;
    }
    
    tr:nth-child(even) {
      background-color: #dddddd;
    }
    .t-table{
        padding-right: 25px;
        width: 50%;

    }
    .t-table table{
        margin-top: 25px;
    }
    .timeline-footer{
        width: 100%;
        /* background-color: red; */
        display: flex;
        justify-content: space-around;
        padding-left: 425px;
    }
    </style>


   
    <script>
        function toggleDropdown() {
          var dropdownContent = document.getElementById("myDropdown");
          dropdownContent.classList.toggle("show");
        }
        </script>

<link href="css/own.css" rel="stylesheet">
<style>
    .card {
 width: 235px;
 box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
 height: 320px;
 padding: .8em;
 background: #f5f5f5;
 position: relative;
 overflow: visible;
 box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
}
.row1{
    display: flex;
    justify-content: space-around;
}

.card-img {
 background-color: #ffcaa6;
 height: 40%;
 width: 100%;
 border-radius: .5rem;
 transition: .3s ease;
}

.card-info {
 padding-top: 10%;
}

svg {
 width: 20px;
 height: 20px;
}

.card-footer {
 width: 100%;
 display: flex;
 justify-content: space-between;
 align-items: center;
 padding-top: 10px;
 border-top: 1px solid #ddd;
}
.t-t{
    color: black;
}
/Text/
.text-title {
 font-weight: 900;
 font-size: 1.2em;
 line-height: 1.5;
}

/* .dbb{
    kground-color: rgb(255, 255, 255);
} */


.text-body {

 font-size: 18px;
 /* padding-top: 10px; */
 
}

/Button/
.card-button {
 border: 1px solid #252525;
 display: flex;
 padding: .3em;
 cursor: pointer;
 border-radius: 50px;
 transition: .3s ease-in-out;
}

/*Hover
.card-img:hover {
 transform: translateY(-25%);
 box-shadow: rgba(226, 196, 63, 0.25) 0px 13px 47px -5px, rgba(180, 71, 71, 0.3) 0px 8px 16px -8px;
}*/

.card-button:hover {
 border: 1px solid #ffcaa6;
 background-color: #ffcaa6;
}

@media(max-width:500px){
    .card{
        width: 80%;
        margin: 10px 0;
        box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
    }
}

.row{
    display: flex;
    justify-content: space-around;
}
.c-i{
height:100%;}

		
		
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
                <a class="nav-link collapsed" href="#" 
                   >
                    <span class="material-symbols-outlined">
                        inventory_2
                        </span>
                    <span>Consingments</span>
                </a>
                
            </li>


            
            
         
            <li class="nav-item">
                <a class="nav-link collapsed" href="TripServlet" 
                   >
                    <span class="material-symbols-outlined">
                       route
                        </span>
                    <span>Active Trip</span>
                </a>
                
            </li>
            <li class="nav-item">
                <a class="nav-link collapsed" href="TripFetchServlet" 
                   >
                    <span class="material-symbols-outlined">
                       route
                        </span>
                    <span>All Trips</span>
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
                        <a class="collapse-item" href="utilities-color.html">view all issues</a>
                        <a class="collapse-item" href="add-route.html">raise vehicle issue</a>
                        <a class="collapse-item" href="utilities-animation.html">raise consignment issue</a>
                        <!-- <a class="collapse-item" href="utilities-other.html">Other</a> -->
                    </div>
                </div>
            </li>

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
                    <h1 style="font-weight: 900" class="text-gray-700">Active Trip</h1>

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
                        	<a style="border-radius:20px;" class="dropdown-item text-gray-200 bg-gradient-danger" href="logout.jsp" data-toggle="modal" data-target="#logoutModal">
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
                <!-- Begin Page Content -->
                <!-- Content Row -->
                <%Trip trip = (Trip)request.getAttribute("trip");
                System.out.println(trip);
                			if(trip!=null && trip.getRemarks()==null){
                		%>
                	<div class="row">
                	
                		<div class="text-center">
                         	<img class="img-fluid px-1 px-sm-1 mt-1 mb-1" style="width: 20rem;" src="img/waiting.jpg" alt="...">
                     	</div>
                     	<h3 class="text-danger" style="text-align: center; ">Trip not started</h3>
                     	
                		<%Vehicle v =  (Vehicle)session.getAttribute("vehicle");%>
                		<div style="display:flex; justify-content: center; " >
                			<div style="width:400px;" class="card shadow">
						      <div class="card-body">
						        <h5 class="card-title">Trip assigned </h5>
						        <h6>Trip id : <%=trip.getTripId() %> </h6>
     						    <h6>Route : <%=trip.getRoute().getRouteName() %> </h6>
     						    <h6>Vehicle id : <%=v.getModel() %> </h6>
     					        <h6>Started on : <%=trip.getTripStartTime() %> </h6>
						        
						        <form method="post" action="TripServlet">
						        	<input hidden name="type" value="start"/>
       							    <button type="submit" class="btn btn-primary">Start Trip</button>
						        </form>
						      </div>
						    </div>
                		</div>
                	</div>
                	<%} %>
                
                    <%if(trip!=null && trip.getRemarks()!=null && trip.getRemarks().equalsIgnoreCase("ongoing")){ %>
					<div class="row">

                        <div  class="time-main-container">
                            <div  class="timeline-container">
                                <div style="overflow:scroll; height:450px;" class="timeline-t">
	                                    <div class="outer-t">
                                    <%List<NextHub> nextHubs = (List<NextHub>) request.getAttribute("nextHub");
                                    	if(nextHubs != null && nextHubs.size()>0){
                                    		for(NextHub n : nextHubs){
                                    %>
                        					<%if(n.getStatus().equals("REACHED")) {%>
                                    			<div class="card-t">
			                                        <div class="info-t bg-gray-300 text-gray-900">
			                                          <h5 class="title-t text-gray-900">Reached</h5>
			                                          <p><%=n.getHubName() %> </p>
			                                          <h6><%=n.getTime() %> </h6>
			                                        </div>
			                                      </div>
						                    <%}else{ %>
			                                 	<div class="card-t">
			                                        <div class="info-t">
			                                          <h5 class="title-t">Upcoming</h5>
			                                          <p><%=n.getHubName() %> </p>
			                                          <h6> </h6>
			                                        </div>
			                                    </div>
			                                 <%} %>
                                    <%} }else{%>
                                    	<h1>no hubs found for this trip maybe admin messed up</h1>
                                    <%} %>
                                    </div>
                                  </div>
                                  <hr>
                            <div class="t-table">
                                    
                            <h3>Current Consingment</h3>
                            
                            <table>
                              <tr>
                                <th>cid</th>
                                <th>Conssignment-name</th>
                                <th>Hub-Address</th>
                                <th>Date</th>
                              </tr>
                             <%List<Consignment> cs =(List<Consignment>) request.getAttribute("currCon");
                             	if(cs!=null && cs.size()>0){
                             		for(Consignment c: cs){
                             	
                             %>
                             <tr>
                                <td><%= c.getConsignmentId()%></td>
                                <td><%= c.getConsignmentName() %></td>
                                <td><%= c.getHub().getHubName() %></td>
                                <td><%= DateHandler.javaToStr(c.getConsignmentDate()) %></td>
                            </tr>
                              <%}}else{ %>
                              <p class="text-danger">No consignment found for this hub please move forward</p>
                              <%} %>
                            </table>
							</div>
                            </div>
                            <%List<Hub> currNext  = (List<Hub>) request.getAttribute("currNext");
                            	System.out.println(currNext);
                            %>
                         <div  class="t-f-c">
                            <div style="padding:10px;" class="footer-left-timeline bg-gray-900 rounded text-gray-400 border border-gray-900">
                              <h5 class="text-gray-600">Current Hub</h3>
                              <%if(currNext.get(0)==null){ %>
                              <h3>Main Hub</h2>
                              <%}else{ %>
                              <h3><%= currNext.get(0).getHubName()%></h3>
                              <%} %>
                             </div>
                             <div style="padding:10px;" class="footer-left-timeline bg-gray-900 rounded text-gray-400 border border-gray-900">
                              <h5 class="text-gray-600">Next Hub</h5>
                              <%if(currNext.get(1)==null){ %>
                              <h3 class="text-success">Finish Trip</h3>
                              <%}else{ %>
                              <h3><%= currNext.get(1).getHubName()%></h3>
                              <p><%= currNext.get(1).getAddress()%></p>
                              <%} %>
                             </div>
                             <%if(currNext.get(1)!= null) {%>
                             <form method="post" action="TripServlet">
						        	<input hidden name="type" value="forward"/>
       							    <button type="submit" class="btn btn-primary">Reached Trip</button>
							</form>
							<%}else {%>
							<form method="post" action="TripServlet">
						        	<input hidden name="type" value="finish"/>
       							    <button type="submit" class="btn btn-primary">Finish Trip</button>
							</form>
							<%} %>
                         </div>
                        </div>
                      
                    </div>
                    <%} %>
                    
                    <%if(trip!=null&& trip.getRemarks()!=null && trip.getRemarks().equalsIgnoreCase("finished")){%>
                    	
                    <div>
                    	<h1>trip finished</h1>
                    	<div class="text-center">
                         	<img class="img-fluid px-1 px-sm-1 mt-1 mb-1" style="width: 20rem;" src="img/waiting.jpg" alt="...">
                     	</div>
                    </div>
                   <%} %>
                   <%if(trip==null){ %>
                   <div  class="text-center conatainer-fluid">
                        <img class="img-fluid px-1 px-sm-1 mt-1 mb-1" style="width: 20rem;" src="img/waiting.jpg" alt="...">
                        <h1>No trips found for you !</h1>
						<button class="btn btn-warning"><a class="text-gray-200" href="TripServlet" >Refresh</a></button>
                    </div>
                    <%} %>
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
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Consignment;
import model.Hub;
import model.Route;
import model.Vehicle;
import utils.DBConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.ConsignmentDao;
import dao.HubDao;
import dao.RouteDao;
import dao.VehicleDao;

/**
 * Servlet implementation class RouteServlet
 */
public class RouteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RouteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBConnection dbConnection = null;
        List<Route> routes = null;
        List <Hub> hs=null;
        try {
            dbConnection = DBConnection.getDbConnnection(); // Get instance of DBConnection
            RouteDao rDao = new RouteDao(dbConnection);
            routes = rDao.findAll();
            HubDao hd =  new HubDao(dbConnection);
            hs = hd.findAll();
        } catch (ClassNotFoundException | 	SQLException e) {
            e.printStackTrace();
        }
	        // Set the filtered consignments as a request attribute
        	HttpSession session = request.getSession();
            session.setAttribute("hubs", hs);
        	session.setAttribute("routes", routes);

	        // Forward the request to the JSP page
	        response.sendRedirect("route-view.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String RouteName = request.getParameter("routeName");
		String StartingPoint = request.getParameter("startingPoint");
		String DestinationPoint = request.getParameter("destinationPoint");

	    try {
	        // Perform database operations to create a new driver entry
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
	        DBConnection dbConnection = DBConnection.getDbConnnection();
	        RouteDao dao = new RouteDao(dbConnection);
	        Route route = new Route(0,RouteName,StartingPoint,DestinationPoint);
	        Route createRoute = dao.create(route);
	
	        if (createRoute != null) {
	            // Driver entry created successfully
	            // Forward the request to the JSP page to display success message or perform further actions
	            request.setAttribute("createdRoute", createRoute);
	            System.out.println("Data Inserted Successfully.");
	//            RequestDispatcher dispatcher = request.getRequestDispatcher("/Driver/Add_driver.jsp");
	//            dispatcher.forward(request, response);
	        } else {
	            // Failed to create driver entry
	            System.out.println("Failed to create route entry.");
	        	}
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	
	    // Redirecting to doGet method to handle GET requests as well
	    doGet(request, response);
	}

}

package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Route;
import utils.DBConnection;

import java.io.IOException;

import dao.RouteDao;

/**
 * Servlet implementation class UpdateRouteServlet
 */
public class UpdateRouteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRouteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int RouteId = Integer.parseInt(request.getParameter("Route_Id")) ;
		System.out.println(RouteId);
		
    	try {
            // Get Route data from the database
    		Class.forName("oracle.jdbc.driver.OracleDriver");
            DBConnection dbConnection = DBConnection.getDbConnnection();
            RouteDao RouteDao = new RouteDao(dbConnection);
            Route Routes = RouteDao.findOne(RouteId);            
            request.setAttribute("Routes", Routes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("route-update.jsp");
            dispatcher.forward(request, response);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getParameter("action");

	    if (action != null && action.equals("Delete")) {
	        // Perform delete operation
	        int RouteId = Integer.parseInt(request.getParameter("Route_Id"));

	        try {
	        	Class.forName("oracle.jdbc.driver.OracleDriver");
	            DBConnection dbConnection = DBConnection.getDbConnnection();
	            RouteDao dao = new RouteDao(dbConnection);

	            boolean deleted = dao.delete(RouteId);

	            if (deleted) {
	                // Deletion successful
	                // Forward the request to the JSP page to display success message or perform further actions
	                request.setAttribute("deleted", deleted);
	                System.out.println("Route record deleted successfully.");
	            } else {
	                // Deletion failed
	                System.out.println("Failed to delete Route record.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } else { // Perform update operation
	        int RouteId = Integer.parseInt(request.getParameter("Route_Id"));
	        String RouteName = request.getParameter("Route_Name");
	        String StartingPoint = request.getParameter("Start_Point");
	        String DestinationPoint = request.getParameter("Destination_Point");

	        try {
	            // Perform database operations to update the Route entry
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            DBConnection dbConnection = DBConnection.getDbConnnection();
	            RouteDao dao = new RouteDao(dbConnection);
	            Route Route = new Route(0,RouteName,StartingPoint,DestinationPoint);
	            boolean updated = dao.update(RouteId, Route);

	            if (updated) {
	                // Update successful
	                // Forward the request to the JSP page to display success message or perform further actions
	                System.out.println("Route record updated successfully.");
	                RequestDispatcher dispatcher = request.getRequestDispatcher("RouteServlet");
	                dispatcher.forward(request, response);
	            } else {
	                // Update failed
	                System.out.println("Failed to update Route record.");
	                request.setAttribute("message", "while updating route");
	                RequestDispatcher dispatcher = request.getRequestDispatcher("404_1.jsp");
	                dispatcher.forward(request, response);
	            }
	            response.sendRedirect("route-view.jsp");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    // After handling the request, redirect to doGet for further processing
	}
}
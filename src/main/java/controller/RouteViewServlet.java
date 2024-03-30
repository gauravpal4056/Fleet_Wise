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
import java.util.List;

import dao.RouteDao;

/**
 * Servlet implementation class RouteViewServlet
 */
public class RouteViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RouteViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            // Get driver data from the database
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            DBConnection dbConnection = DBConnection.getDbConnnection();
            RouteDao RouteDao = new RouteDao(dbConnection);
            List<Route> Routes = RouteDao.findAll();
            
            System.out.println(Routes);
            
//            // Set the driver list as an attribute in the request object
//            request.setAttribute("Drivers", drivers);
//            
//            // Forward the request to the JSP page for displaying the list
//            request.getRequestDispatcher("/Driver/Driver_list.jsp").forward(request, response);
            
            request.setAttribute("Routes", Routes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("route-delete.jsp");
            dispatcher.forward(request, response);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

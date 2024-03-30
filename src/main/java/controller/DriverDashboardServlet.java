package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Consignment;
import model.Driver;
import model.Hub;
import model.Route;
import model.Trip;
import model.Vehicle;
import utils.DBConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.ConsignmentDao;
import dao.DriverDao;
import dao.HubDao;
import dao.RouteDao;
import dao.TripDao;
import dao.VehicleDao;

/**
 * Servlet implementation class DriverDashboardServlet
 */
public class DriverDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverDashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBConnection dbConnection = null;
        HttpSession session = request.getSession();
        if(session.getAttribute("userId")==null) {
        	response.sendRedirect("logout.jsp");
        }
    	int driverId  = (int) session.getAttribute("userId");
		try {
            dbConnection = DBConnection.getDbConnnection(); // Get instance of DBConnection
			VehicleDao vDao = new VehicleDao(dbConnection);
			Vehicle v = vDao.findByDriverId(driverId);
			HubDao hDao = new HubDao(dbConnection);
			List<Hub> hubs = hDao.gethubsfromdriverid(driverId);
			Route r = null;
			if(hubs!=null && hubs.size()>0)
			r = hubs.get(0).getRoute();
			ConsignmentDao cDao = new ConsignmentDao(dbConnection);
			DriverDao dDao = new DriverDao(dbConnection);
			Driver d = dDao.findOne(driverId);
			TripDao tDao = new TripDao(dbConnection);
			Trip trip  = tDao.gettripidfromdriverid(driverId);
			System.out.println(trip);
			List<Consignment> consignments = cDao.getConsignmentsByDriverId(driverId);
			session.setAttribute("driver", d);
			session.setAttribute("route", r);
			session.setAttribute("hubs", hubs);
			session.setAttribute("vehicle",v );
			session.setAttribute("consignments",consignments );
			request.setAttribute("trip", trip);
			request.getRequestDispatcher("indexDriver.jsp").forward(request,  response);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    		}
        
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}

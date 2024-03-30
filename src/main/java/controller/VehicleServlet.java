package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Driver;
import model.Vehicle;
import utils.DBConnection;
import utils.DateHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import dao.VehicleDao;

/**
 * Servlet implementation class VehicleServlet
 */
public class VehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehicleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        DBConnection dbConnection;
		try {
			dbConnection = DBConnection.getDbConnnection();
			List<Vehicle> vehicles = null;

			VehicleDao vDao = new VehicleDao(dbConnection);
	    	vehicles = vDao.findAll();
            request.setAttribute("vehicles", vehicles);
			request.getRequestDispatcher("vehicle-view.jsp").forward(request,  response);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Get instance of DBConnection

        
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DBConnection dbConnection = null;
		try {
			dbConnection = DBConnection.getDbConnnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int driverId = Integer.parseInt(request.getParameter("driver"));
		String regno = request.getParameter("registrationNumber").toUpperCase();
		String model = request.getParameter("model");
		String 	fuelType = request.getParameter("fuelType").toUpperCase();
		String lastmaintained = request.getParameter("lastMaintenanceDate") ;
		int theresholdMonth = Integer.parseInt ( request.getParameter("thresoldmonth") ) ;
		String maintenancedue = request.getParameter("due").toUpperCase() ; 
		
        Date lastMaintenanceDate = null;
		try {
			lastMaintenanceDate = DateHandler.strTojava(lastmaintained);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Vehicle vehicle = new Vehicle();
        vehicle.setDriverId(driverId);
        vehicle.setRegistrationNo(regno);
        vehicle.setModel(model);
        vehicle.setFuelType(fuelType);
        vehicle.setLastMaintained(lastMaintenanceDate);
        vehicle.setThresholdMaintenanceMonths(theresholdMonth);
        vehicle.setMaintenanceDue(maintenancedue);
        
        VehicleDao vDao = new VehicleDao(dbConnection);
        Vehicle res = null;
        try {
			res = vDao.create(vehicle);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        response.sendRedirect("404.jsp");

		}
                
        if (res != null && !response.isCommitted()) 
            response.sendRedirect("vehicle-view.jsp");
//       else {
//            response.sendRedirect("public/404.jsp");
//        }
        
	}

}

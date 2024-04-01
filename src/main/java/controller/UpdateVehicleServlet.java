package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Driver;
import model.Vehicle;
import utils.DBConnection;

import java.io.IOException;

import dao.DriverDao;
import dao.VehicleDao;

/**
 * Servlet implementation class UpdateVehicleServlet
 */
public class UpdateVehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateVehicleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int vehicleId = Integer.parseInt(request.getParameter("vehicle_Id")) ;
		System.out.println(vehicleId);
		
    	try {
            // Get driver data from the database
            DBConnection dbConnection = DBConnection.getDbConnnection();
            VehicleDao vehicleDao = new VehicleDao(dbConnection);
            Vehicle vehicles = vehicleDao.findOne(vehicleId);
                        
            request.setAttribute("vehicles", vehicles);
            RequestDispatcher dispatcher = request.getRequestDispatcher("vehicle-update.jsp");
            dispatcher.forward(request, response);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    int vehicleId = Integer.parseInt(request.getParameter("vehicle_Id"));
		    String registrationNumber = request.getParameter("registrationNumber");
	        String fueltype = request.getParameter("fuelType");
	        String model = request.getParameter("model");
	        int thresold_month = Integer.parseInt(request.getParameter("thresoldmonth"));

	        try {
	            // Perform database operations to update the driver entry
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            DBConnection dbConnection = DBConnection.getDbConnnection();
	            VehicleDao dao = new VehicleDao(dbConnection);
	            Vehicle v = new Vehicle();
	            v.setVehicleId(vehicleId);
	            v.setRegistrationNo(registrationNumber);
	            v.setFuelType(fueltype);
	            v.setThresholdMaintenanceMonths(thresold_month);
	            v.setModel(model);
	            System.out.println(v);
	            //(vehicleId,0,null,registrationNumber,null,model,fueltype,null,thresold_month,null,null,null);
	            boolean updated = dao.update(vehicleId, v);

	            if (updated) {
	                // Update successful
	                // Forward the request to the JSP page to display success message or perform further actions
	                request.setAttribute("updated", updated);
	                System.out.println("Driver record updated successfully.");
	                doGet(request, response);
	            } else {
	                // Update failed
	                System.out.println("Failed to update driver record.");
	                request.setAttribute("message", "Error while updating vehicle!");
	            	RequestDispatcher rd = request.getRequestDispatcher("404_1.jsp");
	            	rd.forward(request, response);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		
	}

}
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
import model.Trip;
import model.Vehicle;
import utils.DBConnection;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  


import dao.ConsignmentDao;
import dao.RouteDao;
import dao.VehicleDao;


public class ConsignmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConsignmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBConnection dbConnection = null;
        HttpSession session = request.getSession();

        List<Consignment> consignments = null;
        List<Vehicle> vehicles = null;
        Route r= null;
        try {
            dbConnection = DBConnection.getDbConnnection(); // Get instance of DBConnection
        	if(request.getParameter("route") != null) 
        	 {
        		int selectedRoute = Integer.parseInt(request.getParameter("route"));
        		System.out.println(selectedRoute);
                ConsignmentDao cDao = new ConsignmentDao(dbConnection);
                consignments = cDao.getConsignmntsByRouteId(selectedRoute);
                RouteDao rDao= new RouteDao(dbConnection);
                r = rDao.findOne(selectedRoute);
                session.setAttribute("selectedRoute", selectedRoute);
                request.setAttribute("selectedRoute", selectedRoute);
                request.setAttribute("selectedRouteName", r.getRouteName());
        	}
        	VehicleDao vDao = new VehicleDao(dbConnection);
        	vehicles = vDao.findAll();
        } catch (ClassNotFoundException | 	SQLException e) {
            e.printStackTrace();
        }
	        // Set the filtered consignments as a request attribute
            
            request.setAttribute("vehicles", vehicles);
        	request.setAttribute("filteredConsignments", consignments);
			request.getRequestDispatcher("consignment-view.jsp").forward(request,  response);

	        // Forward the request to the JSP page
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String type = request.getParameter("type");
		switch(type) {
		
		case "create":
			boolean res=false;
			try {
				res = create(request);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("res", res);
			
		}
		response.sendRedirect("consignment-view.jsp");
		
	}
	
	private boolean create(HttpServletRequest request ) throws SQLException, ClassNotFoundException {
		DBConnection dbConnection;
		dbConnection = DBConnection.getDbConnnection(); 
		Consignment c = new Consignment();
		c.setTrip(null);
		int hubId = Integer.parseInt(request.getParameter("hubId"));
		Hub hub = new Hub();
		hub.setHubId(hubId);
		c.setHub(hub);
		c.setConsignmentAddress(request.getParameter("consignmentAddress"));
		ConsignmentDao cDao = new ConsignmentDao(dbConnection);
		c.setConsignmentName(request.getParameter("consignmentName"));
		c.setStatus("");
		Consignment res =  cDao.create(c);
		if(res!=null) {
			return true;
		}
		
		return false;
	}

}




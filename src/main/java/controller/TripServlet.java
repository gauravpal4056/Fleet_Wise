package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Consignment;
import model.Hub;
import model.NextHub;
import model.Route;
import model.Trip;
import utils.DBConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.ConsignmentDao;
import dao.HubDao;
import dao.NextHubDao;
import dao.TripDao;


public class TripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TripServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBConnection dbConnection = null;
		try {
			dbConnection = DBConnection.getDbConnnection();
			TripDao tDao = new TripDao(dbConnection);
	        HttpSession session = request.getSession();
	        int driverId  = (int) session.getAttribute("userId");
			Trip trip  = tDao.gettripidfromdriverid(driverId);
			
			if(trip.getRemarks()==null) {
				request.setAttribute("trip", trip);
					
			}
			else {
				//fetch all current hub consignment
				
				//fetch next and current hub
				
				//fetch next hub table
			
			}
			
			
			request.getRequestDispatcher("driver-trip.jsp").forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Get instance of DBConnection
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = (String) request.getAttribute("type");

		try {
			switch(type) {
				case "start":
					startTrip(request);
					request.getRequestDispatcher("driver-trip.jsp").forward(request, response);

					break;
				case "forward":
					break;
				case "finish":
					break;
			}
		} catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private HttpServletRequest  startTrip(HttpServletRequest request) throws SQLException, ServletException, IOException {
		DBConnection dbConnection = null;
		TripDao tDao = new TripDao(dbConnection);
    	HttpSession session = request.getSession();
		Trip trip = tDao.getTripByDriverId((int)session.getAttribute("userId"));

		//change status of trip
		
		trip.setRemarks("PENDING");
		tDao.update(trip.getTripId(), trip);
		
		//change consignment status
		ConsignmentDao cDao = new ConsignmentDao(dbConnection);
		cDao.updateStatusByTripId(trip.getTripId());
		
		//creating hub next
		NextHubDao nDao = new NextHubDao(dbConnection);
		HubDao hDao = new HubDao(dbConnection);
		List<Hub> hubs = hDao.getHubsByRouteId(trip.getRoute().getRouteId());
		nDao.createAll(hubs, trip);
		List<NextHub> nextHubs = nDao.findByTripId(trip.getTripId());
		
		//setting payload in request
		
		List<Consignment> hubConsignment = null;
		if(nextHubs!=null && nextHubs.size()>0)
			hubConsignment = cDao.getConsignmentsByHubId(nextHubs.get(0).getHubId());
		request.setAttribute("next-hub", nextHubs);
		request.setAttribute("trip", trip);
		request.setAttribute("currentConsignment", hubConsignment);

		
		return request;
	}
	
	private boolean forward() {
		return true;
	}

}

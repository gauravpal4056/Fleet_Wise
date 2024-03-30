package controller;

import jakarta.servlet.RequestDispatcher;
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
import dao.VehicleDao;


public class TripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TripServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBConnection dbConnection = null;
		HttpSession session = request.getSession();
        if(session.getAttribute("userId")==null) {
        	response.sendRedirect("logout.jsp");
        } 
		try {
			dbConnection = DBConnection.getDbConnnection();
			TripDao tDao = new TripDao(dbConnection);
	        if(session.getAttribute("userId")==null || session.getAttribute("userId")=="") response.sendRedirect("logout.jsp");
	        int driverId  = (int) session.getAttribute("userId");
			Trip trip  = tDao.gettripidfromdriverid(driverId);
			ConsignmentDao cDao = new ConsignmentDao(dbConnection);
			NextHubDao nDao = new NextHubDao(dbConnection);

			List<Consignment> consignments = null;
			List<Consignment> currCon = null;
			if(trip == null) {
				
				request.setAttribute("trip", trip);	
		 		request.getRequestDispatcher("driver-trip.jsp").forward(request, response);
			}
			else if(trip!=null && trip.getRemarks()==null) {
				consignments = cDao.getConsignmentsByTripId(trip.getTripId());
				request.setAttribute("consignments", consignments);	
				request.setAttribute("trip", trip);	
				
				request.getRequestDispatcher("driver-trip.jsp").forward(request, response);
			}
			else if(trip!=null && trip.getRemarks().equalsIgnoreCase("ongoing")) {
				List<Hub> currNext = nDao.fetchNextCurrent(trip);
				System.out.println("cur next hub is " + currNext);
				List<NextHub> res = nDao.findByTripId(trip.getTripId());
				if(currNext.get(1)!= null) {
					currCon = cDao.getConsignmentsByHubId(currNext.get(1).getHubId(), trip.getTripId());
				}
				System.out.println("curr next hubb is" + currNext);

				consignments = cDao.getConsignmentsByTripId(trip.getTripId());
				request.setAttribute("consignments", consignments);	
				request.setAttribute("trip", trip);	
				request.setAttribute("currCon", currCon);	
				request.setAttribute("currNext", currNext);	
				request.setAttribute("nextHub", res);
				request.getRequestDispatcher("driver-trip.jsp").forward(request, response);
			}
			else {
				request.setAttribute("trip", trip);	
				request.getRequestDispatcher("driver-trip.jsp").forward(request, response);

			}
			
			
			

							//fetch all current hub consignment
				
				//fetch next and current hub
				
				//fetch next hub table
			
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Get instance of DBConnection
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = (String) request.getParameter("type");
		System.out.println(type);
		try {
			switch(type) {
				case "start":
					startTrip(request);
					doGet(request, response);
					break;
				case "forward":
					forward(request);
					doGet(request, response);

					break;
				case "finish":
					finish(request);
					doGet(request, response);
					break;
			}
		} catch (SQLException | ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private HttpServletRequest  startTrip(HttpServletRequest request) throws SQLException, ServletException, IOException, ClassNotFoundException {
		DBConnection dbConnection = DBConnection.getDbConnnection();
		TripDao tDao = new TripDao(dbConnection);
    	HttpSession session = request.getSession();
		Trip trip = tDao.gettripidfromdriverid((int)session.getAttribute("userId"));

		//change status of trip
		
		trip.setRemarks("ONGOING");
		tDao.update(trip.getTripId(), trip);
		
		//change consignment status
		ConsignmentDao cDao = new ConsignmentDao(dbConnection);
		System.out.println(cDao.updateStatusByTripId(trip.getTripId(), "RUNNING") + "status updated of consgnment");
		
		//creating hub next
		NextHubDao nDao = new NextHubDao(dbConnection);
		HubDao hDao = new HubDao(dbConnection);
		List<Hub> hubs = hDao.getHubsByRouteId(trip.getRoute().getRouteId());		
		System.out.println(nDao.createAll(hubs, trip) + "hubnext created");
		List<NextHub> nextHubs = nDao.findByTripId(trip.getTripId());
		VehicleDao vDao = new VehicleDao(dbConnection);
		vDao.setVehicleStatus("ACTIVE", trip.getVehicle().getVehicleId());
		//setting payload in request
		
		List<Consignment> hubConsignment = null;
		if(nextHubs!=null && nextHubs.size()>0)
			hubConsignment = cDao.getConsignmentsByHubId(nextHubs.get(0).getHubId(), trip.getTripId());
		request.setAttribute("next-hub", nextHubs);
		request.setAttribute("trip", trip);
		request.setAttribute("currentConsignment", hubConsignment);

		
		return request;
	}
	
	private boolean forward(HttpServletRequest request) throws SQLException, ClassNotFoundException {
		System.out.println("trip moved forward");
		DBConnection dbConnection = DBConnection.getDbConnnection();
		TripDao tDao = new TripDao(dbConnection);
    	HttpSession session = request.getSession();
		Trip trip = tDao.gettripidfromdriverid((int)session.getAttribute("userId"));
		NextHubDao nDao = new NextHubDao(dbConnection);
		ConsignmentDao cDao = new ConsignmentDao(dbConnection);

		//get the first  hub and set status to rweached with timestamp
		System.out.println(nDao.updateFirstUpcomingHubStatus(trip) + " hub changed to reachhed");
		
		//update status consignment status
		System.out.println(cDao.updateStatusHubReached(trip.getTripId())+ " consignmetn changed to deliver");
		return true;
	}
	
	private boolean finish(HttpServletRequest request) throws SQLException, ClassNotFoundException  {
		DBConnection dbConnection = DBConnection.getDbConnnection();
		TripDao tDao = new TripDao(dbConnection);
    	HttpSession session = request.getSession();
		Trip trip = tDao.gettripidfromdriverid((int)session.getAttribute("userId"));
		NextHubDao nDao = new NextHubDao(dbConnection);
		ConsignmentDao cDao = new ConsignmentDao(dbConnection);

		
		
		//get the first  hub and set status to rweached with timestamp
		nDao.updateFirstUpcomingHubStatus(trip);
		//update status consignment status
		cDao.updateStatusHubReached(trip.getTripId());

		//update status of trip
		trip.setRemarks("FINISHED");
		tDao.update(trip.getTripId(), trip);
		//make vehicle availabel
		
		VehicleDao vDao = new VehicleDao(dbConnection);
		vDao.setVehicleStatus("INACTIVE", trip.getVehicle().getVehicleId());
		
		return true;
	}

}

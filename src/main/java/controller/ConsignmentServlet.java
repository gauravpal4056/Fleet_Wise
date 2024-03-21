package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Consignment;
import model.Hub;
import model.Trip;
import utils.DBConnection;

import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  


import dao.ConsignmentDao;


public class ConsignmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConsignmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
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
			System.out.println(res);
			request.setAttribute("res", res);
		}
		doGet(request, response);
	}
	
	private boolean create(HttpServletRequest request ) throws SQLException, ClassNotFoundException {
		DBConnection dbConnection;
		dbConnection = DBConnection.getDbConnnection(); 
//		this.consignmentId = consignmentId;
//		this.trip = trip;
//		this.hub = hub;
//		this.consignmentDate = consignmentDate;
//		this.consignmentName = consignmentName;
//		this.consignmentAddress = consignmentAddress;
//		this.status = status;
		Consignment c = new Consignment();
		c.setTrip(null);
		int hubId = Integer.parseInt(request.getParameter("hubId"));
		Hub hub = new Hub();
		hub.setHubId(hubId);
		c.setHub(hub);
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
		String consignmentDate = dateFormat.format(date);  
		c.setConsignmentDate(consignmentDate);
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




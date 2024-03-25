package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Driver;
import utils.DBConnection;

import java.io.IOException;
import java.sql.Connection;

import dao.DriverDao;

/**
 * Servlet implementation class DriverServlet
 */
public class DriverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBConnection dbConnection = null;
		try {
			dbConnection = DBConnection.getDbConnnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		DriverDao dDao = new DriverDao(dbConnection);
		String driverName = request.getParameter("driverName");
        String driverGender = request.getParameter("driverGender");
        int driverAge = Integer.parseInt(request.getParameter("driverAge")); // Convert string to int
        String licenceNumber = request.getParameter("licenceNumber");
        long driverPhone = Long.parseLong(request.getParameter("driverPhone")); // Convert string to long
        String driverEmail = request.getParameter("driverEmail");
        String driverAddress = request.getParameter("driverAddress");
        
        Driver d = new Driver();
        d.setName(driverName);
        d.setAddress(driverAddress);
        d.setAge(driverAge);
        d.setEmailAddress(driverAddress);
        d.setGender(driverGender.toUpperCase());
        d.setPhoneNumber(driverPhone);
        d.setLicenceNo(licenceNumber);
        d.setEmailAddress(driverEmail);
        System.out.println(d);
        Driver res = dDao.create(d);
        
//        if(res!=null)
//        	response.sendRedirect("public/driver-view.jsp"); 
//        response.sendRedirect("public/404.jsp");
        if (res != null && !response.isCommitted()) 
        	response.sendRedirect("public/driver-view.jsp"); 
       else {
            response.sendRedirect("public/404.jsp");
        }

	}

}

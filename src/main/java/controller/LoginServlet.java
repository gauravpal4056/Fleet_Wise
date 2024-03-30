package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Admin;
import model.Driver;

import java.io.IOException;


import dao.AdminDao;
import dao.DriverDao;
import utils.DBConnection;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String idType = request.getParameter("type").toUpperCase();
        HttpSession session = request.getSession();
        System.out.println(idType);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
	    	DBConnection dbConnection ;
	    	dbConnection = DBConnection.getDbConnnection();
	    	if(idType.equals("ADMIN")){
	    		AdminDao aDao = new AdminDao(dbConnection);
	    		Admin res = aDao.findByNamePassword(username, password);
	    		if (res!=null) {
	            	session.setAttribute("user", idType);
	            	session.setAttribute("userId", res.getAdminId());
	                response.sendRedirect("index.jsp");
	            } else {
	            	System.out.println("NO ADMINN FOUND");
	            	request.setAttribute("message", "No Admin Found !");
	            	RequestDispatcher rd = request.getRequestDispatcher("public/404_1.jsp");
	            	rd.forward(request, response);
	            }
	    	}else {
	    		DriverDao dDao = new DriverDao(dbConnection);
	    		Driver res = dDao.findByNamePassword(username, password);
	    		if (res!=null) {
	            	session.setAttribute("user", idType);
	            	session.setAttribute("userId", res.getDriverId());
	            	RequestDispatcher rd = request.getRequestDispatcher("DriverDashboardServlet");
	            	rd.forward(request, response);
	            } else {
	            	System.out.println("NO DRVIER FOUND");
	            	request.setAttribute("message", "No Driver Found !");
	            	RequestDispatcher rd = request.getRequestDispatcher("public/404_1.jsp");
	            	rd.forward(request, response);
	            }
	    	}
        } catch (Exception e) {
            System.out.println("EXCEPTION OCCURED ON LOGIN");

            e.printStackTrace();
        }
	}

}

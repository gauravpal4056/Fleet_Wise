package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;

import java.sql.*;

import dao.UserDao;
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
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
	    	DBConnection dbConnection ;
	    	dbConnection = DBConnection.getDbConnnection();
    		UserDao dao = new UserDao(dbConnection);
    		User user = dao.findByUsernameAndPassword(username, password, idType);
            if (user!=null) {
            	System.out.println("FOUND");
            	System.out.println(username.toString());
            	HttpSession session = request.getSession();

            	session.setAttribute("user", idType);
            	
            } else {
            	System.out.println("NOT FOUND");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		doGet(request, response);
	}

}

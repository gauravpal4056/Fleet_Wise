package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.*;

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
		String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String idType = request.getParameter("type").toUpperCase();
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        	DBConnection dbConnection ;
        	dbConnection = DBConnection.getDbConnnection();
           PreparedStatement ps = dbConnection.getConnection().prepareStatement("select * from USERS where Username=? and PASSWORD=? and id_type = ?" );
           ps.setString(1, userName);
           ps.setString(2, password);
           ps.setString(3, idType);
           ResultSet rs = ps.executeQuery();
            ps.setString(1, userName);
            if (rs.next()) {
            	request.setAttribute("user", rs.getString("id_type"));
            } else {
            	System.out.println("NOT FOUND");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		doGet(request, response);
	}

}

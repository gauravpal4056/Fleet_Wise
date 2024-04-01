 package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Admin;
import utils.DBConnection;

import java.io.IOException;
import java.util.List;

import dao.AdminDao;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBConnection dbConnection;
		try {
			dbConnection = DBConnection.getDbConnnection();
			AdminDao aDao = new AdminDao(dbConnection);
			List<Admin> admins = aDao.findAll();

			request.setAttribute("admins", admins);
			RequestDispatcher rd = request.getRequestDispatcher("admin-view.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBConnection dbConnection;
		try {
			dbConnection = DBConnection.getDbConnnection();
			String name = request.getParameter("fullname");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			int phone = Integer.parseInt(request.getParameter("phone"));
			String gender = request.getParameter("gender");
			Admin admin = new Admin();
			admin.setEmailAddress(email);
			admin.setGender(gender);
			admin.setPhoneNumber(phone);
			admin.setName(name);
			admin.setPassword(password);
			
			AdminDao aDao = new AdminDao(dbConnection);
			aDao.create(admin);
			doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
	}

}

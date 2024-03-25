package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.AdminService;
import utils.DBConnection;

import java.io.IOException;
import java.sql.SQLException;

public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ScheduleServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBConnection dbConnection = null;
		try {
			dbConnection = DBConnection.getDbConnnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		int selectedVehicle = Integer.parseInt(request.getParameter("vehicle"));
        HttpSession session = request.getSession();
        int selectedRoute= (int) session.getAttribute("selectedRoute");
        AdminService as = new AdminService(dbConnection);
        try {
			as.scheduleAllConsignments(selectedVehicle, selectedRoute);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
	}

}

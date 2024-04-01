package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Consignment;
import model.Driver;
import model.Issue;
import model.Vehicle;
import utils.DBConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.IssueDao;

/**
 * Servlet implementation class IssueServlet
 */
public class IssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public IssueServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			DBConnection dbConnection = DBConnection.getDbConnnection();
			IssueDao iDao = new IssueDao(dbConnection);
			HttpSession session = request.getSession();
	        int driverId = (int) session.getAttribute("userId");
			List<Issue> is = iDao.getAllIssueByDriver(driverId);
			System.out.println(driverId);
			request.setAttribute("issues", is);
			request.getRequestDispatcher("driver-issue-view.jsp").forward(request, response);
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	        // Perform database operations to create a new driver entry
	        DBConnection dbConnection = DBConnection.getDbConnnection();
	        IssueDao dao = new IssueDao(dbConnection);
	        Issue i = new Issue();
	        String remark = request.getParameter("remark");
	        String description = request.getParameter("description");
	        HttpSession session = request.getSession();
	        int driverId = (int) session.getAttribute("userId");
	        int consignmentId = Integer.parseInt(request.getParameter("consignment_Id")) ;
	        Consignment c = new Consignment();
	        c.setConsignmentId(consignmentId);
	        i.setConsignment(c);
	        Driver d= new Driver();
	        d.setDriverId(driverId);
	        i.setDriver(d);
	        i.setDescription(description);
	        i.setRemarks(remark);
	        dao.addConsignmentIssue(i);
	        
            doGet(request, response);
	        
	    } catch (Exception e) {
            System.out.println("Failed to create route entry.");

	        e.printStackTrace();
	    }

//  
		doGet(request, response);
		
		
	}

}
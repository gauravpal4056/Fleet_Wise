package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Issue;
import services.AdminService;
import utils.DBConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.IssueDao;

/**
 * Servlet implementation class AdminIssue
 */
public class AdminIssue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIssue() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			DBConnection dbConnection = DBConnection.getDbConnnection();
			IssueDao iDao = new IssueDao(dbConnection);
			List<Issue> is = iDao.getAllIssues();
			request.setAttribute("issues", is);
			request.getRequestDispatcher("issues-consignment-view.jsp").forward(request, response);
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			DBConnection dbConnection = DBConnection.getDbConnnection();
			int selectedIssue = Integer.parseInt(request.getParameter("issue"));
	        AdminService as = new AdminService(dbConnection);
	        as.solveIssue(selectedIssue);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
        
	}

}

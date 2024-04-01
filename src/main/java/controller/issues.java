package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Issue;
import utils.DBConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.IssueDao;

/**
 * Servlet implementation class issues
 */
public class issues extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public issues() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			DBConnection dbConnection = DBConnection.getDbConnnection();
			IssueDao iDao = new IssueDao(dbConnection);
			HttpSession session = request.getSession();
	        int driverId = (int) session.getAttribute("userId");
			List<Issue> is = iDao.getAllIssueByDriver(driverId);
			request.setAttribute("issues", is);
			request.getRequestDispatcher("driver-issue-view.jsp").forward(request, response);
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Trip;
import utils.DBConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.TripDao;

/**
 * Servlet implementation class TripFetchServletAdmin
 */
public class TripFetchServletAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TripFetchServletAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
	        if(session.getAttribute("user")==null) {
	        	response.sendRedirect("logout.jsp");
	        }
	        if(session.getAttribute("user")==null || session.getAttribute("user")=="") response.sendRedirect("logout.jsp");
			try {
				DBConnection dbConnection = DBConnection.getDbConnnection();
		    	TripDao tDao = new TripDao(dbConnection);

				List<Trip> trips  = tDao.findAll();
				request.setAttribute("trips", trips);
				request.getRequestDispatcher("trip-view.jsp").forward(request, response);
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
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

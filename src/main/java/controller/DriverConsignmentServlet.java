package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Consignment;
import utils.DBConnection;

import java.io.IOException;
import java.util.List;

import dao.ConsignmentDao;

/**
 * Servlet implementation class DriverConsignmentServlet
 */
public class DriverConsignmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverConsignmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBConnection dbConnection;
		try {
			dbConnection = DBConnection.getDbConnnection();
			HttpSession session = request.getSession();
	        int driverId =  (int) session.getAttribute("userId");
			System.out.println(driverId);

	        ConsignmentDao cDao = new ConsignmentDao(dbConnection);
	        List<Consignment> ls = cDao.getConsignmentsByDriverId(driverId);
	        request.setAttribute("consignments", ls);
	        System.out.println(ls);
	        request.getRequestDispatcher("driver-consignment-view.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
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

package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import dao.ConsignmentDao;
import jakarta.servlet.http.HttpSession;
import model.Consignment;
import model.Vehicle;
import utils.DBConnection;

public class AdminService {
//	public void addVehicle(Vehicle vehicle) {
//		VehicleDao vDao = new VehicleDaobbConnectoion();
//		vDao.create(vehicle;)
//	}
	 private DBConnection dbConnection;

	    public AdminService(DBConnection dbConnection) {
	        this.dbConnection = dbConnection;
	    }
	
	    public boolean scheduleAllConsignments(int vehicleid, int routeId) throws SQLException {
	    	boolean res = true;
	    	ConsignmentDao cDao = new ConsignmentDao(dbConnection);
	    	List<Consignment> cs =cDao.getConsignmntsByRouteId(routeId);
	    	for(Consignment c: cs) {
	    		res=res&&scheduleOneConsignment(vehicleid, c.getConsignmentId(), routeId);
	    	}
	    	
	    	return res;
	//		CallableStatement cstmt = connection.prepareCall("{ ? = call TRIP_R_V( ? ) }");
	//
	//		cstmt.registerOutParameter(1, Types.NUMERIC);
	//
	//		cstmt.setInt(2, consignmentid);
	//
	//		cstmt.execute();
	//
	//		int routId = cstmt.getInt(1);
	    }
	    
	    public boolean scheduleOneConsignment(int vehicleid, int consignmentid, int routId) throws SQLException {
	    	boolean res = true;
	    	Connection connection = dbConnection.getConnection();
	    	String query2 = "select TRIP_ID from trips where route_id = ? and vehicle_id = ? and remarks is null";
	        PreparedStatement pstmt3 = connection.prepareStatement(query2);
	        pstmt3.setInt(1, routId);
	        pstmt3.setInt(2, vehicleid);
	        

	        ResultSet rs = pstmt3.executeQuery();
	        
	        if (rs.next()) 
	        {
	        	System.out.println("existing trip found");
	            int x = rs.getInt("TRIP_ID");
	            String query4 = " update consignments set trip_id = ? where CONSIGNMENT_ID = ?  ";
	            PreparedStatement pstmt4 = connection.prepareStatement(query4);
	            pstmt4.setInt(1, x);
	            pstmt4.setInt(2, consignmentid);
	            
	            int rowsAffected = pstmt4.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("trip inserted into consignment");
	            } else {
	            	res = false;
	                System.out.println("Failed to insert trip into consignment");
	            }
	            
	            String upd_qry = " update consignments set STATUS = ? where CONSIGNMENT_ID = ?  ";
	            PreparedStatement pstmt_upt = connection.prepareStatement(upd_qry);
	            pstmt_upt.setString(1,"RECIEVED" );
	            pstmt_upt.setInt(2, consignmentid);
	            
	            int r = pstmt_upt.executeUpdate();

	            if (r > 0) {
	                System.out.println("consignment STATUS CHANGED");
	            } else {
	            	res = false;
	                System.out.println("Failed to CHANGE the consignment status");
	            }  
	        } 
	        else {
	            String query = "insert into Trips (route_id, vehicle_id) values (?, ?)";
	            try {
	                PreparedStatement pstmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
	                pstmt.setInt(1, routId);
	                pstmt.setInt(2, vehicleid);
	                pstmt.executeUpdate();
	                System.out.println("new trip created");
	    		 	String query5 = "select TRIP_ID from trips where route_id = ? and vehicle_id = ?";
	    	       PreparedStatement pstmt5 = connection.prepareStatement(query5);
	    	        pstmt5.setInt(1, routId);
	    	        pstmt5.setInt(2, vehicleid);
	    	        ResultSet rset = pstmt5.executeQuery();
	    	        if (rset.next()) 
	    	        {
	    	            int y = rset.getInt("TRIP_ID");
		                System.out.println("created trip ID  " + y );
	    	            String query6 = " update consignments set trip_id = ? where CONSIGNMENT_ID = ?  ";
	    	            PreparedStatement pstmt6 = connection.prepareStatement(query6);
	    	            pstmt6.setInt(1, y);
	    	            pstmt6.setInt(2, consignmentid);
	    	            int rowsAffected = pstmt6.executeUpdate();
	    	            if (rowsAffected > 0) {
	    	                System.out.println("trip inserted into consignment");
	    	            } else {
	    	            	res = false;
	    	                System.out.println("Failed to insert trip");
	    	            }
	    	            String upd_qry2 = " update consignments set STATUS = ? where CONSIGNMENT_ID = ?  ";
	    	            PreparedStatement pstmt_upt2 = connection.prepareStatement(upd_qry2);
	    	            pstmt_upt2.setString(1,"RECIEVED" );
	    	            pstmt_upt2.setInt(2, consignmentid);
	    	            
	    	            int rowno = pstmt_upt2.executeUpdate();
	    	            if (rowno > 0) {
	    	            	
	    	            	
	    	                System.out.println("consignment STATUS CHANGED to received");
	    	            } else {
	    	            	res = false;
	    	                System.out.println("Failed  CHANGE the status");
	    	            }
	    	        }  
	    	        
    	            
	            } catch (SQLException e) {
	            	res = false;
	                System.out.println("Error: " + e.getMessage());
	                System.out.println("error creating trip ");
	            }
	            
	            
	        }
            return res;

	        
	    }
	    
	
	
}

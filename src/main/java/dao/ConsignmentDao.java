package dao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Consignment;
import model.Hub;
import model.Trip;
import utils.DBConnection;
import utils.DateHandler;

public class ConsignmentDao implements IDao<Consignment> {

    private DBConnection dbConnection;

    public ConsignmentDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Consignment create(Consignment consignment) throws SQLException {
        //Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        System.out.println(consignment.toString());
        try {
        	Connection connection = dbConnection.getConnection();
            String query = "INSERT INTO consignments (hub_Id, consignment_Name, consignment_Address, status) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, consignment.getHub().getHubId());
            statement.setString(2,consignment.getConsignmentName());
            statement.setString(3,consignment.getConsignmentAddress());
            statement.setString(4,consignment.getStatus());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating consignment failed, no rows affected.");
            }
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
            	System.out.println(generatedKeys.getString(1));
                //consignment.setConsignmentId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating consignment failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        
        return consignment;
    }

    @Override
    public boolean update(int id, Consignment consignment) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null; 
        try {
            connection = dbConnection.getConnection();
            String query = "UPDATE consignments SET hub_Id=?, trip_Id=?, consignment_Name=?, consignment_Address=?, status=? WHERE consignment_Id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, consignment.getHub().getHubId());
            statement.setInt(2, consignment.getTrip().getTripId());
            statement.setString(3, consignment.getConsignmentName());
            statement.setString(4, consignment.getConsignmentAddress());
            statement.setString(5, consignment.getStatus());
            statement.setInt(6, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
    }
    
    public boolean updateStatusByTripId(int id, String status) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null; 
        try {
            connection = dbConnection.getConnection();
            String query = "UPDATE consignments SET status=? WHERE trip_Id=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, status);
            statement.setInt(2, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnection.getConnection();
            String query = "DELETE FROM consignments WHERE consignment_Id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
    }

    @Override
    public Consignment findOne(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Consignment consignment = null;
        try {
            connection = dbConnection.getConnection();
            String query = "SELECT * FROM consignments WHERE consignment_id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                consignment = extractConsignmentFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return consignment;
    }
    
    public boolean updateStatusHubReached(int tripId) throws SQLException {
    	Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Consignment consignment = null;
        int hubId;
        try {
            connection = dbConnection.getConnection();
            String query = "SELECT hub_id FROM next_hub WHERE trip_id=? order by timestamp";
            statement = connection.prepareStatement(query);
            statement.setInt(1, tripId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                hubId = resultSet.getInt("hub_id");
                query = "update consignments set status='DELIVERED' where hub_id=? and trip_id=? ";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hubId);
                statement.setInt(2, tripId);

                resultSet = statement.executeQuery();
                return true;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return false;
    }
    
    @Override
    public List<Consignment> findAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Consignment> consignments = new ArrayList<>();
        try {
            connection = dbConnection.getConnection();
            String query = "SELECT * FROM consignments";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Consignment consignment = extractConsignmentFromResultSet(resultSet);
                consignments.add(consignment);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return consignments;
    }
    
    public List<Consignment> getConsignmentsByTripId(int tripId) throws SQLException {
        List<Consignment> consignments = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnection.getConnection();
            String query = "SELECT * FROM consignments WHERE trip_Id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, tripId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Consignment consignment = extractConsignmentFromResultSet(resultSet);
                consignments.add(consignment);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return consignments;
    }
    
    public List<Consignment> getConsignmentsByHubId(int hubId, int tripId) throws SQLException {
        List<Consignment> consignments = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnection.getConnection();
            String query = "SELECT * FROM consignments WHERE hub_Id=? and trip_id =?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, hubId);
            statement.setInt(2, tripId);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Consignment consignment = extractConsignmentFromResultSet(resultSet);
                consignments.add(consignment);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return consignments;
    }
    
    public List<Consignment> getConsignmntsByRouteId(int routeId) throws SQLException {
        List<Consignment> consignments = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnection.getConnection();
            String query = "SELECT c.Consignment_ID, c.Trip_ID, c.hub_id, c.Consignment_Date, c.Consignment_name, c.Consignment_address, c.Status FROM consignments c inner join hubs h on h.hub_id = c.hub_id  WHERE h.route_Id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, routeId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Consignment consignment = extractConsignmentFromResultSet(resultSet);
                consignments.add(consignment);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return consignments;
    }
    

    public List<Consignment> getConsignmentsByDriverId(int driverid) {
    		Consignment consignment = new Consignment();

    		List<Consignment> consignments = new ArrayList<>();

    		try {
    			Connection connection = dbConnection.getConnection();
    			PreparedStatement statement = connection.prepareStatement("SELECT distinct c.consignment_id FROM consignments c JOIN hubs h ON c.hub_id = h.hub_id JOIN trips t ON h.route_id = t.route_id JOIN vehicles v ON t.vehicle_id = v.vehicle_id WHERE v.driver_id = ?");
    			statement.setInt(1, driverid);
    			ResultSet resultset = statement.executeQuery();

    			while (resultset.next()) {
    				
    				consignments.add(findOne(resultset.getInt("consignment_id")));

    			}

    		} catch (Exception e) {
    			System.out.println(e);
    		}

    		return consignments;

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

    private Consignment extractConsignmentFromResultSet(ResultSet resultSet) throws SQLException {
        Consignment consignment = new Consignment();
        consignment.setConsignmentId(resultSet.getInt("consignment_Id"));
        Hub hub = new Hub();
        hub.setHubId(resultSet.getInt("hub_Id"));
        consignment.setHub(hub);
        Trip trip = new Trip();
        trip.setTripId(resultSet.getInt("trip_Id"));
        //Trip trip = tripDao.findOne(resultSet.getInt("trip_Id"));
        consignment.setTrip(trip);
        consignment.setConsignmentDate(DateHandler.sqlTimeToJava(resultSet.getTimestamp("consignment_Date")));
        consignment.setConsignmentName(resultSet.getString("consignment_Name"));
        consignment.setConsignmentAddress(resultSet.getString("consignment_Address"));
        consignment.setStatus(resultSet.getString("status"));
        return consignment;
    }
}

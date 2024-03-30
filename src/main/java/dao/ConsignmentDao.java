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
    			PreparedStatement statement = connection.prepareStatement(
    					"select consignment_id from consignments where hub_id = (select hub_id from hubs where route_id = (select route_id from trips where vehicle_id = (select vehicle_id from vehicles where driver_id = ?) ))");
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

    private Consignment extractConsignmentFromResultSet(ResultSet resultSet) throws SQLException {
        Consignment consignment = new Consignment();
        consignment.setConsignmentId(resultSet.getInt("consignment_Id"));
        HubDao hubDao = new HubDao(dbConnection);
        Hub hub = hubDao.findOne(resultSet.getInt("hub_Id"));
        consignment.setHub(hub);
        TripDao tripDao = new TripDao(dbConnection);
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

package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Consignment;
import model.Hub;
import model.NextHub;
import model.Trip;
import utils.DBConnection;

public class NextHubDao implements IDao<NextHub> {
	private DBConnection dbConnection;

    public NextHubDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    // Method to insert a new NextHub record into the database
    public NextHub create(NextHub nextHub) throws SQLException {
        Connection connection = dbConnection.getConnection();

        String sql = "INSERT INTO next_hub (Hub_ID, Trip_ID, hub_name,   Status ) VALUES (?,?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, nextHub.getHubId());
            statement.setInt(2, nextHub.getTripId());
            statement.setString(3, nextHub.getHubName());
            statement.setString(4, nextHub.getStatus());
            statement.executeUpdate();
        }
        return nextHub;
    }
    
    public boolean createAll(List<Hub> hubs, Trip trip) throws SQLException {
        
            for (Hub hub : hubs) {
            	NextHub nh = new NextHub();
            	nh.setHubId(hub.getHubId());
            	nh.setTripId(trip.getTripId());
            	nh.setHubName(hub.getHubName());
            	nh.setStatus("UPCOMING");
            	create(nh);
            }
            return true;
        }
    
    

    // Method to retrieve all NextHub records from the database
    public List<NextHub> findAll() throws SQLException {
        Connection connection = dbConnection.getConnection();

        List<NextHub> nextHubs = new ArrayList<>();
        String sql = "SELECT * FROM next_hub";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                NextHub nextHub = new NextHub();
                nextHub.setNextHubId(resultSet.getInt("Next_Hub_ID"));
                nextHub.setHubId(resultSet.getInt("Hub_ID"));
                nextHub.setTripId(resultSet.getInt("Trip_ID"));
                nextHub.setStatus(resultSet.getString("Status"));
                nextHubs.add(nextHub);
            }
        }
        return nextHubs;
    }

    // Method to update a NextHub record in the database
    public boolean update(int id, NextHub nextHub) throws SQLException {
        Connection connection = dbConnection.getConnection();

        String sql = "UPDATE next_hub SET Hub_ID=?, Trip_ID=?, Status=? WHERE Next_Hub_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, nextHub.getHubId());
            statement.setInt(2, nextHub.getTripId());
            statement.setString(3, nextHub.getStatus());
            statement.setInt(4, id);
            statement.executeUpdate();
            return true;
        }
        
        
    }

    // Method to delete a NextHub record from the database
    public boolean delete(int nextHubId) throws SQLException {
        Connection connection = dbConnection.getConnection();
        String sql = "DELETE FROM next_hub WHERE Next_Hub_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, nextHubId);
            statement.executeUpdate();
            return true;
        }
    }



	@Override
	public NextHub findOne(int nextHubId) throws SQLException {
        Connection connection = dbConnection.getConnection();

        NextHub nextHub = null;
        String sql = "SELECT * FROM next_hub WHERE Next_Hub_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, nextHubId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    nextHub = mapResultSetToNextHub(resultSet);
                }
            }
        }
        return nextHub;
    }
	
	public List<NextHub>findByTripId(int tripId) throws SQLException {
        Connection connection = dbConnection.getConnection();

        List<NextHub> nextHubs = new ArrayList<>();
        String sql = "SELECT * FROM next_hub WHERE Trip_ID = ? order by hub_id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, tripId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    NextHub nextHub = mapResultSetToNextHub(resultSet);
                    nextHubs.add(nextHub);
                }
            }
        }
        return nextHubs;
    }
	
	private NextHub mapResultSetToNextHub(ResultSet resultSet) throws SQLException {

        NextHub nextHub = new NextHub();
        nextHub.setHubId(resultSet.getInt("Hub_ID"));
        nextHub.setHubName(resultSet.getString("HUB_NAME"));
        nextHub.setTripId(resultSet.getInt("Trip_ID"));
        nextHub.setTime(resultSet.getTimestamp("Timestamp").toString());
        nextHub.setStatus(resultSet.getString("Status"));
        return nextHub;
    }

	
	public boolean updateStatus(Trip trip , String status) throws SQLException {
        Connection connection = dbConnection.getConnection();
		String sql = "UPDATE next_hub Status=? WHERE trip_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setInt(2, trip.getTripId());
            statement.executeUpdate();
            return true;
        }
	}
	
	public boolean updateFirstUpcomingHubStatus(Trip trip) {
		
		//String sql = "UPDATE next_hub Status=? WHERE trip_id=? and hub_id = ?";
		String sqlUpdate = "UPDATE next_hub SET Status = 'REACHED', Timestamp = sysdate WHERE Status = 'UPCOMING' AND Next_Hub_ID = (SELECT MIN(Next_Hub_ID) FROM next_hub WHERE Trip_id = ? and Status = 'UPCOMING')";
	    
	    try (Connection connection = dbConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
	    	preparedStatement.setInt(1, trip.getTripId());
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected == 0) {
	            System.out.println("No hubs with status 'UPCOMING' found to update.");
	            return false;
	        } else {
	            System.out.println("First hub with status 'UPCOMING' updated to reached.");
	            return true;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle or log the exception as needed
	    }
	    return false;
	}
	public List<Hub> fetchNextCurrent (Trip trip) throws SQLException{
		List<Hub> res = new ArrayList<>();
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        HubDao hDao = new HubDao(dbConnection);
        
        try {
        	
            connection = dbConnection.getConnection();
            String currentStop = "select * from next_hub where trip_id=? AND status = 'REACHED' order by timestamp desc fetch first 1 row only";
            String nextStop = "select * from next_hub where trip_id=? AND status = 'UPCOMING'";
            statement = connection.prepareStatement(currentStop);
            statement.setInt(1, trip.getTripId());
            resultSet = statement.executeQuery();
            Hub hub = null;
            if (resultSet.next()) {
            	
            	NextHub nextHub = mapResultSetToNextHub(resultSet);
            	if (nextHub != null) {
            		hub = hDao.findOne(nextHub.getHubId());
            		
            	}
            	
            }
            res.add(hub);
            
            statement = connection.prepareStatement(nextStop);
            statement.setInt(1, trip.getTripId());
            resultSet = statement.executeQuery();
            Hub hubNext = null;
            if (resultSet.next()) {
           	NextHub nextHub = mapResultSetToNextHub(resultSet);
            	if (nextHub != null) {
            		hubNext = hDao.findOne(nextHub.getHubId());
            		}
            	
            }
            res.add(hubNext);
            System.out.println("from next hub  dao curu next is" + res);
            return res;
            
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
		
	}

    
}

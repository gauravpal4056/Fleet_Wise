package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

        String sql = "INSERT INTO next_hub (Hub_ID, Trip_ID, Status) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, nextHub.getHubId());
            statement.setInt(2, nextHub.getTripId());
            statement.setString(3, nextHub.getStatus());
            statement.executeUpdate();
        }
        return nextHub;
    }
    
    public boolean createAll(List<Hub> hubs, Trip trip) throws SQLException {
        Connection connection = dbConnection.getConnection();
    	String sql = "INSERT INTO next_hub (Hub_ID, Trip_ID, Status) VALUES (?, ?, 'UPCOMING')";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Hub hub : hubs) {
                statement.setInt(1, hub.getHubId());
                statement.setInt(2, trip.getTripId());
                statement.addBatch();
            }
            statement.executeBatch();
            return true;
        }
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
        String sql = "SELECT * FROM next_hub WHERE Trip_ID = ?";
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
        nextHub.setNextHubId(resultSet.getInt("Next_Hub_ID"));
        nextHub.setHubId(resultSet.getInt("Hub_ID"));
        nextHub.setTripId(resultSet.getInt("Trip_ID"));
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


    
}

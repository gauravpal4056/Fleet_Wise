
package dao;

import model.Trip;
import model.Consignment;
import model.Route;
import model.Vehicle;
import utils.DBConnection;
import utils.DateHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TripDao implements IDao<Trip> {

    private DBConnection dbConnection;

    public TripDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Trip create(Trip trip) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        try {
            connection = dbConnection.getConnection();
            String query = "INSERT INTO trips (route_Id, vehicle_Id, trip_Start_Time, trip_End_Time, remarks) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, trip.getRoute().getRouteId());
            statement.setInt(2, trip.getVehicle().getVehicleId());
            statement.setString(3, trip.getTripStartTime());
            statement.setString(4, trip.getTripEndTime());
            statement.setString(5, trip.getRemarks());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating trip failed, no rows affected.");
            }
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                trip.setTripId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating trip failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return trip;
    }

    @Override
    public boolean update(int id, Trip trip) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnection.getConnection();
            String query = "UPDATE trips SET route_Id=?,trip_end_time=sysdate, vehicle_Id=?, remarks=? WHERE trip_Id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, trip.getRoute().getRouteId());
            statement.setInt(2, trip.getVehicle().getVehicleId());
            statement.setString(3, trip.getRemarks());
            statement.setInt(4, id);
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
            String query = "DELETE FROM trips WHERE trip_Id=?";
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
    public Trip findOne(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Trip trip = null;
        try {
            connection = dbConnection.getConnection();
            String query = "SELECT * FROM trips WHERE trip_Id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                trip = extractTripFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return trip;
    }

    @Override
    public List<Trip> findAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Trip> trips = new ArrayList<>();
        try {
            connection = dbConnection.getConnection();
            String query = "SELECT * FROM trips";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Trip trip = extractTripFromResultSet(resultSet);
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return trips;
    }
    
    public Trip getTripByDriverId(int driverId) throws SQLException {
        Connection connection = null;
        connection = dbConnection.getConnection();
        Trip trip = null;
        String sql = "SELECT * FROM Trips WHERE Driver_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
        	
            statement.setInt(1, driverId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    trip = extractTripFromResultSet(resultSet);
                }
            }
        }
        return trip;
    }
    
    public Trip gettripidfromdriverid(int driverid) throws SQLException {
		Trip trip = null;

		Connection connection = dbConnection.getConnection();
		String query = " select trip_id from trips where remarks!= 'FINISHED' OR remarks is null and vehicle_id = (select vehicle_id from vehicles where driver_id = ? ) ";

		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, driverid);
		ResultSet resultset = statement.executeQuery();
		while (resultset.next()) {
			int tripid = resultset.getInt("trip_id");
			trip = findOne(tripid);
		}

		return trip;
	}
    
    public List<Trip> findAllByDriverId(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Trip> trips = new ArrayList<>();
        try {
            connection = dbConnection.getConnection();
    		String query = " select * from trips where vehicle_id = (select vehicle_id from vehicles where driver_id = ? ) ";

            //String query = "SELECT * FROM trips";
            statement = connection.prepareStatement(query);
    		statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Trip trip = extractTripFromResultSet(resultSet);
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return trips;
    }

    private Trip extractTripFromResultSet(ResultSet resultSet) throws SQLException {
        Trip trip = new Trip();
        trip.setTripId(resultSet.getInt("trip_Id"));
        RouteDao rDao = new RouteDao(dbConnection);	
        Route route = rDao.findOne(resultSet.getInt("route_Id"));
        trip.setRoute(route);
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(resultSet.getInt("vehicle_id"));
        trip.setVehicle(vehicle);
        
        trip.setTripStartTime(resultSet.getString("TRIP_START_TIME"));
        trip.setTripEndTime(resultSet.getString("TRIP_END_TIME"));
        
        //trip.setTripStartTime(DateHandler.sqlTimeToStr(resultSet.getTimestamp("trip_Start_Time")));
        //trip.setTripEndTime(DateHandler.sqlTimeToStr(resultSet.getTimestamp("trip_End_Time")));
        trip.setRemarks(resultSet.getString("remarks"));
        //List<Consignment> consignments = consignmentDao.getConsignmentsByTripId(resultSet.getInt("trip_Id"));
        trip.setConsignments(null);
        return trip;
    }
    
}

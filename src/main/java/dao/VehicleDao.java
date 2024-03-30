//package dao;
//
////public class VehicleDao {
////	
////}
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import model.Vehicle;
//import utils.DBConnection;
//import utils.DateHandler;
//
//public class VehicleDao {
//
//	private DBConnection dbConnection;
//
//    public VehicleDao(DBConnection dbConnection) {
//        this.dbConnection = dbConnection;
//    }
//	public String addvehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException {
//	    Connection connection = dbConnection.getConnection();
//
//	    String result = "";
//	    
//	    String sql = "insert into Vehicles (driver_id , registration_no , model , fuel_type , last_maintained , threshold_maintenance_months , maintenance_due,status) values (?,? ,?, ?, ?, ?, ?, ?,? )";
//	    result = "data inserted successfully";
//
//	    PreparedStatement ps = null;
//
//	    try {
//	        ps = connection.prepareStatement(sql);
//
//	        int driverId = vehicle.getDriverId();
//
//	        System.out.println("driver id : " + driverId);
//
//	        ps.setInt(1, vehicle.getDriverId());
//	        ps.setString(2, vehicle.getRegistrationNo());
//	        ps.setString(3, vehicle.getModel());
//	        ps.setString(4, vehicle.getFuelType());
//	        ps.setTimestamp(5, DateHandler.javaToSql(vehicle.getLastMaintained()));
//	        ps.setInt(6, vehicle.getThresholdMaintenanceMonths());
//	        ps.setString(8, vehicle.getMaintenanceDue());
//	        ps.setString(9, vehicle.getStatus());
//
//	        ps.executeUpdate();
//
//	        System.out.println("data inserted ");
//
//	        System.out.println("Calling UPDATE DRIVER METHOD . .  . .  .  ........");
//
//	        String update = updateDriverById(driverId);
//
//	        System.out.println("Data updated: " + update);
//
//	    } catch (SQLException e) {
//	        result = "Insert failed: " + e.getMessage();
//	        e.printStackTrace();
//	    } finally {
//	        if (ps != null) {
//	            try {
//	                ps.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	        if (connection != null) {
//	            try {
//	                connection.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	    }
//
//	    return result;
//	}
//
//	public String updateDriverById(int id) throws SQLException, ClassNotFoundException {
//		String update = "null";
//		Connection connection = null;
//		try {
//
//			connection = dbConnection.getConnection();
//	    	System.out.println("connection successfully .....");
//			PreparedStatement preparedStatement = connection
//					.prepareStatement("update Drivers set available='FALSE' where Driver_ID = ?");
//
//			preparedStatement.setInt(1, id);
//			System.out.println("bbbbb");
//			
//			int rowsAffected = preparedStatement.executeUpdate();
//			
//			System.out.println("rowsAffected" + rowsAffected);
//			
//			
//			if (rowsAffected > 0) {
//				update = "updated successfully";
//				System.out.println(update);
//			} else {
//				update = "No records found for the given driver ID";
//				System.out.println(update);
//			}
//		}
//
//		catch (SQLException e) {
//			update = "Update failed: " + e.getMessage();
//			e.printStackTrace();
//		}
//		finally {
//	        if (connection != null) {
//	            try {
//	                connection.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	    }
//		return update;
//	}
//	
//	
//
//}


package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Vehicle;
import utils.DBConnection;
import utils.DateHandler;

public class VehicleDao implements IDao<Vehicle> {
	private DBConnection dbConnection;

    public VehicleDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Vehicle create(Vehicle vehicle) throws SQLException {
    	System.out.println(vehicle);
        String query = "INSERT INTO vehicles (driver_id, registration_no, model, fuel_type, last_maintained, threshold_maintenance_months, maintenance_due) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection=dbConnection.getConnection();
        	PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, vehicle.getDriverId());
            statement.setString(2, vehicle.getRegistrationNo());
            statement.setString(3, vehicle.getModel());
            statement.setString(4, vehicle.getFuelType());
            statement.setDate(5, DateHandler.javaToSqlDate(vehicle.getLastMaintained()));
            statement.setInt(6, vehicle.getThresholdMaintenanceMonths());
            statement.setString(7, vehicle.getMaintenanceDue());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating vehicle failed, no rows affected.");
            }
        }
        return vehicle;
    }

    @Override
    public boolean update(int id, Vehicle vehicle) throws SQLException {
        String query = "UPDATE vehicles SET driver_id=?, registration_no=?, registration_date=?, model=?, fuel_type=?, last_maintained=?, threshold_maintenance_months=?, next_maintenance=?, maintenance_due=?, status=? WHERE vehicle_id=?";
        try (Connection connection=dbConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, vehicle.getDriverId());
            statement.setString(2, vehicle.getRegistrationNo());
            statement.setString(3, vehicle.getRegistrationDate());
            statement.setString(4, vehicle.getModel());
            statement.setString(5, vehicle.getFuelType());
            statement.setDate(6, DateHandler.javaToSqlDate(vehicle.getLastMaintained()));
            statement.setInt(7, vehicle.getThresholdMaintenanceMonths());
            statement.setDate(8, DateHandler.javaToSqlDate(vehicle.getNextMaintenance()));
            statement.setString(9, vehicle.getMaintenanceDue());
            statement.setString(10, vehicle.getStatus());
            statement.setInt(11, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    public boolean setVehicleStatus(String status, int id) throws SQLException {
    	String query = "UPDATE vehicles SET status=? WHERE vehicle_id=?";
    	try (Connection connection=dbConnection.getConnection();
    			PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(2, id);
            statement.setString(1, status);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
    	}
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM vehicles WHERE vehicle_id=?";
        try (Connection connection=dbConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public Vehicle findOne(int id) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE vehicle_id=?";
        try (Connection connection=dbConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToVehicle(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public List<Vehicle> findAll() throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";
        try (Connection connection=dbConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                vehicles.add(mapResultSetToVehicle(resultSet));
            }
        }
        return vehicles;
    }
    
    public List<Vehicle> findAllInctiveVehicles() throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE status='INACTIVE'";
        try (Connection connection=dbConnection.getConnection();
        		PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                vehicles.add(mapResultSetToVehicle(resultSet));
            }
        }
        return vehicles;
    }
    public Vehicle findByDriverId(int driverId) throws SQLException {
    	Vehicle vehicle = null;
        String query = "SELECT * FROM Vehicles WHERE Driver_ID = ?";
        Connection connection=dbConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, driverId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
            	if (resultSet.next()) {
                    return mapResultSetToVehicle(resultSet);
                }
            }
         }
        
        return vehicle;
    }
    
    public boolean updateStatus(int vehicleid) throws SQLException {
        Connection connection=dbConnection.getConnection();
        boolean res = true;
    	String upd_qry3 = " update vehicles set STATUS = ? where vehicle_id = ?  ";
        PreparedStatement pstmt_upt2 = connection.prepareStatement(upd_qry3);
        pstmt_upt2.setString(1,"ACTIVE" );
        pstmt_upt2.setInt(2, vehicleid);
        int rowno = pstmt_upt2.executeUpdate();
        if (rowno > 0) {
            System.out.println("vehicle  STATUS ");
            return res;
        } else {
        	res = false;
            System.out.println("Failed  CHANGE the vehicle status");
        }
        return res;
    }

    private Vehicle mapResultSetToVehicle(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(resultSet.getInt("vehicle_id"));
        vehicle.setDriverId(resultSet.getInt("driver_id"));
        vehicle.setRegistrationNo(resultSet.getString("registration_no"));
        vehicle.setRegistrationDate(resultSet.getString("registration_date"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setFuelType(resultSet.getString("fuel_type"));
        vehicle.setLastMaintained(DateHandler.javaToSqlDate(resultSet.getDate("last_maintained")));
        vehicle.setThresholdMaintenanceMonths(resultSet.getInt("threshold_maintenance_months"));
        vehicle.setNextMaintenance(DateHandler.javaToSqlDate(resultSet.getDate("next_maintenance")));
        vehicle.setMaintenanceDue(resultSet.getString("maintenance_due"));
        vehicle.setStatus(resultSet.getString("status"));
        return vehicle;
    }
}


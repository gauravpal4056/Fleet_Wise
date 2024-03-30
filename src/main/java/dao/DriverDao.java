
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Driver;
import utils.DBConnection;
import utils.DateHandler;

public class DriverDao implements IDao<Driver> {

	private DBConnection dbConnection;
	private List<Driver> drivers = new ArrayList<>();
	
	public DriverDao() {
		super();
	}

	public DriverDao(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}

	@Override
	public Driver create(Driver driver) {
		
		try {
			Connection connection=dbConnection.getConnection();
			String sqlQuery="insert into drivers (name,gender, age,Licence_NO,Phone_Number,Email_Address,Address) values(?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, driver.getName());
			preparedStatement.setString(2, driver.getGender());
			preparedStatement.setInt(3, driver.getAge());
			preparedStatement.setString(4, driver.getLicenceNo());
			preparedStatement.setLong(5, driver.getPhoneNumber());
			preparedStatement.setString(6, driver.getEmailAddress());
			preparedStatement.setString(7, driver.getAddress());
			
			if(preparedStatement.executeUpdate()<0) {
				driver=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return driver;
	}

	@Override
	public boolean update(int id, Driver driver) {
		boolean result=false;
		try {
			Connection connection=dbConnection.getConnection();
			String sqlQuery="update drivers set name=?,gender=?,age=?,Licence_NO=?,Phone_Number=?,Email_Address=?,Address=?,Available=? where driver_Id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, driver.getName());
			preparedStatement.setString(2, driver.getGender());
			preparedStatement.setInt(3, driver.getAge());
			preparedStatement.setString(4, driver.getLicenceNo());
			preparedStatement.setLong(5, driver.getPhoneNumber());
			preparedStatement.setString(6, driver.getEmailAddress());
			preparedStatement.setString(7, driver.getAddress());
			preparedStatement.setString(8, "TRUE");
			preparedStatement.setInt(9, id);
			if(preparedStatement.executeUpdate()>0) {
				result=true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result=false;
		try {
			Connection connection=dbConnection.getConnection();
			String sqlQuery="delete from drivers where driver_Id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			if(preparedStatement.executeUpdate()>0) {
				result=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}                     
	

	@Override
	public Driver findOne(int id) throws SQLException {
		Connection connection=dbConnection.getConnection();
		final String sqlQuery="select * from drivers where driver_Id=?";
		PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery); 
		preparedStatement.setInt(1, id);
		ResultSet resultSet=preparedStatement.executeQuery();
		Driver driver=null;    
		if(resultSet.next()) {
        	driver = new Driver(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getLong(6),resultSet.getString(7),resultSet.getString(8),DateHandler.sqlDateToJava(resultSet.getDate(9)), resultSet.getString(10));
		}
		
		return driver;
	}
	
	public Driver findByNamePassword(String drivername, String licence) throws SQLException {
		PreparedStatement ps = dbConnection.getConnection().prepareStatement("select * from drivers where name=? AND Licence_NO= ? " );
        ps.setString(1, drivername);
        ps.setString(2, licence);
        ResultSet resultSet = ps.executeQuery();
        Driver driver = null;
        if (resultSet.next()) {
        	driver = new Driver(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getLong(6),resultSet.getString(7),resultSet.getString(8),DateHandler.sqlDateToJava(resultSet.getDate(9)), resultSet.getString(10));
        }
		return driver;
	}

	@Override
	public List<Driver> findAll() throws SQLException {

		Connection connection = dbConnection.getConnection();
		Statement selectStatement = connection.createStatement();

		final String sqlQuery = "select * from drivers";
		ResultSet resultSet = selectStatement.executeQuery(sqlQuery);
        Driver driver = null;
		while (resultSet.next()) {
			//using column names
//			driver driver = new driver(resultSet.getInt("driverId"), resultSet.getString("driverName"),resultSet.getString("email"));
			// using column position in result
        	driver = new Driver(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getLong(6),resultSet.getString(7),resultSet.getString(8),DateHandler.sqlDateToJava(resultSet.getDate(9)), resultSet.getString(10));
			drivers.add(driver);

		}
		if (drivers.isEmpty())
			return null;
		return drivers;
	}
	
	public List<Driver> findAllAvailable() throws SQLException {

		Connection connection = dbConnection.getConnection();
		Statement selectStatement = connection.createStatement();

		final String sqlQuery = "select * from drivers where available='TRUE'";
		ResultSet resultSet = selectStatement.executeQuery(sqlQuery);
        Driver driver = null;
		while (resultSet.next()) {
			//using column names
//			driver driver = new driver(resultSet.getInt("driverId"), resultSet.getString("driverName"),resultSet.getString("email"));
			// using column position in result
        	driver = new Driver(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getLong(6),resultSet.getString(7),resultSet.getString(8),DateHandler.sqlDateToJava(resultSet.getDate(9)), resultSet.getString(10));
			drivers.add(driver);

		}
		if (drivers.isEmpty())
			return null;
		return drivers;
	}
	

}
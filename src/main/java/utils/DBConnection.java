package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static DBConnection dbConnnection;
	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String username = "fleet_wise";
	private final String password = "root";
	private Connection connection;

	private DBConnection() {
		super();
	}

	public static DBConnection getDbConnnection() throws ClassNotFoundException {
		
		if (dbConnnection == null) {
			
			synchronized (DBConnection.class) {
				if (dbConnnection == null) {
		            Class.forName("oracle.jdbc.driver.OracleDriver");
					dbConnnection = new DBConnection();
				}
			}

		}

		return dbConnnection;
	}

	public Connection getConnection() throws SQLException {
		if (connection == null ||connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
			if (!connection.isClosed()) {
				System.out.println(connection.isClosed());
				return connection;
			}
			
		}
		return connection;
	}

	public boolean closeConnection() throws SQLException {
		if(connection!=null) {
			connection.close();
		}
		return connection.isClosed();
	}

}

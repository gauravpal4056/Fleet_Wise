package services;

import java.sql.SQLException;
import java.util.List;

import dao.ConsignmentDao;
import dao.HubDao;
import dao.RouteDao;
import dao.VehicleDao;
import model.Consignment;
import model.Hub;
import model.Route;
import model.Vehicle;
import utils.DBConnection;

public class DriverServices {
	
	private DBConnection dbConnection;

    public DriverServices(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
	
	public List<List<Object>>  payload(int driverId) throws SQLException {
		
		List<Object> payload
	}
}

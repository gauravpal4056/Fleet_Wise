package services;

import model.Vehicle;

public class AdminService {
	public void addVehicle(Vehicle vehicle) {
		VehicleDao vDao = new VehicleDaobbConnectoion();
		vDao.create(vehicle;)
	}
}

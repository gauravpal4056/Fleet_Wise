package model;

import java.util.Date;
import java.util.Objects;

public class Vehicle {
	private int vehicleId;
	//one to one
    private int driverId;
    private String registrationNo;
    private String registrationDate;
    private String model;
    private String fuelType;
    private Date lastMaintained;
    private int thresholdMaintenanceMonths;
    private Date nextMaintenance;
    private String maintenanceDue;
    private String status;
	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vehicle(int vehicleId, int driverId, String registrationNo, String registrationDate, String model,
			String fuelType, Date lastMaintained, int thresholdMaintenanceMonths, Date nextMaintenance,
			String maintenanceDue, String status) {
		super();
		this.vehicleId = vehicleId;
		this.driverId = driverId;
		this.registrationNo = registrationNo;
		this.registrationDate = registrationDate;
		this.model = model;
		this.fuelType = fuelType;
		this.lastMaintained = lastMaintained;
		this.thresholdMaintenanceMonths = thresholdMaintenanceMonths;
		this.nextMaintenance = nextMaintenance;
		this.maintenanceDue = maintenanceDue;
		this.status = status;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public Date getLastMaintained() {
		return lastMaintained;
	}
	public void setLastMaintained(Date lastMaintained) {
		this.lastMaintained = lastMaintained;
	}
	public int getThresholdMaintenanceMonths() {
		return thresholdMaintenanceMonths;
	}
	public void setThresholdMaintenanceMonths(int thresholdMaintenanceMonths) {
		this.thresholdMaintenanceMonths = thresholdMaintenanceMonths;
	}
	public Date getNextMaintenance() {
		return nextMaintenance;
	}
	public void setNextMaintenance(Date nextMaintenance) {
		this.nextMaintenance = nextMaintenance;
	}
	public String getMaintenanceDue() {
		return maintenanceDue;
	}
	public void setMaintenanceDue(String maintenanceDue) {
		this.maintenanceDue = maintenanceDue;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", driverId=" + driverId + ", registrationNo=" + registrationNo
				+ ", registrationDate=" + registrationDate + ", model=" + model + ", fuelType=" + fuelType
				+ ", lastMaintained=" + lastMaintained + ", thresholdMaintenanceMonths=" + thresholdMaintenanceMonths
				+ ", nextMaintenance=" + nextMaintenance + ", maintenanceDue=" + maintenanceDue + ", status=" + status
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(driverId, fuelType, lastMaintained, maintenanceDue, model, nextMaintenance,
				registrationDate, registrationNo, status, thresholdMaintenanceMonths, vehicleId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		return driverId == other.driverId && Objects.equals(fuelType, other.fuelType)
				&& Objects.equals(lastMaintained, other.lastMaintained)
				&& Objects.equals(maintenanceDue, other.maintenanceDue) && Objects.equals(model, other.model)
				&& Objects.equals(nextMaintenance, other.nextMaintenance)
				&& Objects.equals(registrationDate, other.registrationDate)
				&& Objects.equals(registrationNo, other.registrationNo) && Objects.equals(status, other.status)
				&& thresholdMaintenanceMonths == other.thresholdMaintenanceMonths && vehicleId == other.vehicleId;
	}
	
	
}

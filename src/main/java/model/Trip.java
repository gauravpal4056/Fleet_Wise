package model;

import java.util.List;
import java.util.Objects;

public class Trip {
	private int tripId;
	//one to one
    private Route routeId;
    private Vehicle vehicleId;
    //one to many
    private List<Consignment> consignments;
    
    private String tripStartTime;
    private String tripEndTime;
    private String remarks;
	public Trip() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Trip(int tripId, Route routeId, Vehicle vehicleId, List<Consignment> consignments, String tripStartTime,
			String tripEndTime, String remarks) {
		super();
		this.tripId = tripId;
		this.routeId = routeId;
		this.vehicleId = vehicleId;
		this.consignments = consignments;
		this.tripStartTime = tripStartTime;
		this.tripEndTime = tripEndTime;
		this.remarks = remarks;
	}
	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	public Route getRouteId() {
		return routeId;
	}
	public void setRouteId(Route routeId) {
		this.routeId = routeId;
	}
	public Vehicle getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Vehicle vehicleId) {
		this.vehicleId = vehicleId;
	}
	public List<Consignment> getConsignments() {
		return consignments;
	}
	public void setConsignments(List<Consignment> consignments) {
		this.consignments = consignments;
	}
	public String getTripStartTime() {
		return tripStartTime;
	}
	public void setTripStartTime(String tripStartTime) {
		this.tripStartTime = tripStartTime;
	}
	public String getTripEndTime() {
		return tripEndTime;
	}
	public void setTripEndTime(String tripEndTime) {
		this.tripEndTime = tripEndTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "Trip [tripId=" + tripId + ", routeId=" + routeId + ", vehicleId=" + vehicleId + ", consignments="
				+ consignments + ", tripStartTime=" + tripStartTime + ", tripEndTime=" + tripEndTime + ", remarks="
				+ remarks + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(consignments, remarks, routeId, tripEndTime, tripId, tripStartTime, vehicleId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trip other = (Trip) obj;
		return Objects.equals(consignments, other.consignments) && Objects.equals(remarks, other.remarks)
				&& Objects.equals(routeId, other.routeId) && Objects.equals(tripEndTime, other.tripEndTime)
				&& tripId == other.tripId && Objects.equals(tripStartTime, other.tripStartTime)
				&& Objects.equals(vehicleId, other.vehicleId);
	}
    
    
    
    
    
}

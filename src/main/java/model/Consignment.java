package model;

import java.util.Date;
import java.util.Objects;

public class Consignment {
	private int consignmentId;
    private Trip trip;
    private Hub hub;
    private Date consignmentDate;
	private String consignmentName;
    private String consignmentAddress;
    private String status;
    
	public Consignment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Consignment(int consignmentId, Trip trip, Hub hub, Date consignmentDate, String consignmentName,
			String consignmentAddress, String status) {
		super();
		this.consignmentId = consignmentId;
		this.trip = trip;
		this.hub = hub;
		this.consignmentDate = consignmentDate;
		this.consignmentName = consignmentName;
		this.consignmentAddress = consignmentAddress;
		this.status = status;
	}

	public int getConsignmentId() {
		return consignmentId;
	}

	public void setConsignmentId(int consignmentId) {
		this.consignmentId = consignmentId;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Hub getHub() {
		return hub;
	}

	public void setHub(Hub hub) {
		this.hub = hub;
	}

	public Date getConsignmentDate() {
		return consignmentDate;
	}

	public void setConsignmentDate(Date consignmentDate) {
		this.consignmentDate = consignmentDate;
	}

	public String getConsignmentName() {
		return consignmentName;
	}

	public void setConsignmentName(String consignmentName) {
		this.consignmentName = consignmentName;
	}

	public String getConsignmentAddress() {
		return consignmentAddress;
	}

	public void setConsignmentAddress(String consignmentAddress) {
		this.consignmentAddress = consignmentAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Consignment [consignmentId=" + consignmentId + ", trip=" + trip + ", hub=" + hub + ", consignmentDate="
				+ consignmentDate + ", consignmentName=" + consignmentName + ", consignmentAddress="
				+ consignmentAddress + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(consignmentAddress, consignmentDate, consignmentId, consignmentName, hub, status, trip);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consignment other = (Consignment) obj;
		return Objects.equals(consignmentAddress, other.consignmentAddress)
				&& Objects.equals(consignmentDate, other.consignmentDate) && consignmentId == other.consignmentId
				&& Objects.equals(consignmentName, other.consignmentName) && Objects.equals(hub, other.hub)
				&& Objects.equals(status, other.status) && Objects.equals(trip, other.trip);
	}

	
	
}

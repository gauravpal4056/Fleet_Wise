package model;

import java.util.List;
import java.util.Objects;

public class Consignment {
	private int consignmentId;
	//one to many
	private List<Issue> consignmentIssues;
	//one to one
    private Trip trip;
    private Hub hub;
    private String consignmentDate;
	private String consignmentName;
    private String consignmentAddress;
    private String status;
    
    public Consignment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Consignment(int consignmentId, List<Issue> issues, Trip trip, Hub hub, String consignmentDate,
			String consignmentName, String consignmentAddress, String status) {
		super();
		this.consignmentId = consignmentId;
		this.consignmentIssues = issues;
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

	public List<Issue> getIssues() {
		return consignmentIssues;
	}

	public void setIssues(List<Issue> issues) {
		this.consignmentIssues = issues;
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

	public String getConsignmentDate() {
		return consignmentDate;
	}

	public void setConsignmentDate(String consignmentDate) {
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
		return "Consignment [consignmentId=" + consignmentId + ", issues=" + consignmentIssues + ", trip=" + trip + ", hub=" + hub
				+ ", consignmentDate=" + consignmentDate + ", consignmentName=" + consignmentName
				+ ", consignmentAddress=" + consignmentAddress + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(consignmentAddress, consignmentDate, consignmentId, consignmentName, hub, consignmentIssues, status,
				trip);
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
				&& Objects.equals(consignmentIssues, other.consignmentIssues) && Objects.equals(status, other.status)
				&& Objects.equals(trip, other.trip);
	}
    
    
}

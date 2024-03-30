package model;

import java.util.Date;
import java.util.Objects;

public class Issue {
	private int issueId;
    private Driver driver;

    //many to one
	private Vehicle vehicle;
    private Consignment consignment;
    private String description;
    private Date raisedOn;
    private Date resolvedOn;
    private String remarks;
    private String status;
	public Issue() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Issue(int issueId, Driver driver, Vehicle vehicle, Consignment consignment, String description,
			Date raisedOn, Date resolvedOn, String remarks, String status) {
		super();
		this.issueId = issueId;
		this.driver = driver;
		this.vehicle = vehicle;
		this.consignment = consignment;
		this.description = description;
		this.raisedOn = raisedOn;
		this.resolvedOn = resolvedOn;
		this.remarks = remarks;
		this.status = status;
	}
	public int getIssueId() {
		return issueId;
	}
	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public Consignment getConsignment() {
		return consignment;
	}
	public void setConsignment(Consignment consignment) {
		this.consignment = consignment;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getRaisedOn() {
		return raisedOn;
	}
	public void setRaisedOn(Date raisedOn) {
		this.raisedOn = raisedOn;
	}
	public Date getResolvedOn() {
		return resolvedOn;
	}
	public void setResolvedOn(Date resolvedOn) {
		this.resolvedOn = resolvedOn;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Issue [issueId=" + issueId + ", driver=" + driver + ", vehicle=" + vehicle + ", consignment="
				+ consignment + ", description=" + description + ", raisedOn=" + raisedOn + ", resolvedOn=" + resolvedOn
				+ ", remarks=" + remarks + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(consignment, description, driver, issueId, raisedOn, remarks, resolvedOn, status, vehicle);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Issue other = (Issue) obj;
		return Objects.equals(consignment, other.consignment) && Objects.equals(description, other.description)
				&& Objects.equals(driver, other.driver) && issueId == other.issueId
				&& Objects.equals(raisedOn, other.raisedOn) && Objects.equals(remarks, other.remarks)
				&& Objects.equals(resolvedOn, other.resolvedOn) && Objects.equals(status, other.status)
				&& Objects.equals(vehicle, other.vehicle);
	}
	
	
	
    
    
}

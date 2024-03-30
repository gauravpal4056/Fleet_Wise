package model;

import java.util.Objects;

public class NextHub {
	private int nextHubId;
    private int hubId;
    private String hubName;
    private String time;
    private Integer tripId;
    private String status;
	public NextHub() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NextHub(int nextHubId, int hubId, String hubName, String time, Integer tripId, String status) {
		super();
		this.nextHubId = nextHubId;
		this.hubId = hubId;
		this.hubName = hubName;
		this.time = time;
		this.tripId = tripId;
		this.status = status;
	}
	public int getNextHubId() {
		return nextHubId;
	}
	public void setNextHubId(int nextHubId) {
		this.nextHubId = nextHubId;
	}
	public int getHubId() {
		return hubId;
	}
	public void setHubId(int hubId) {
		this.hubId = hubId;
	}
	public String getHubName() {
		return hubName;
	}
	public void setHubName(String hubName) {
		this.hubName = hubName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getTripId() {
		return tripId;
	}
	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		return Objects.hash(hubId, hubName, nextHubId, status, time, tripId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NextHub other = (NextHub) obj;
		return hubId == other.hubId && Objects.equals(hubName, other.hubName) && nextHubId == other.nextHubId
				&& Objects.equals(status, other.status) && Objects.equals(time, other.time)
				&& Objects.equals(tripId, other.tripId);
	}
	@Override
	public String toString() {
		return "NextHub [nextHubId=" + nextHubId + ", hubId=" + hubId + ", hubName=" + hubName + ", time=" + time
				+ ", tripId=" + tripId + ", status=" + status + "]";
	}
	
}

package model;

import java.util.Objects;

public class NextHub {
	private int nextHubId;
    private int hubId;
    private Integer tripId;
    private String status;
	public NextHub() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NextHub(int nextHubId, int hubId, Integer tripId, String status) {
		super();
		this.nextHubId = nextHubId;
		this.hubId = hubId;
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
	public String toString() {
		return "NextHub [nextHubId=" + nextHubId + ", hubId=" + hubId + ", tripId=" + tripId + ", status=" + status
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(hubId, nextHubId, status, tripId);
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
		return hubId == other.hubId && nextHubId == other.nextHubId && Objects.equals(status, other.status)
				&& Objects.equals(tripId, other.tripId);
	}
    
}

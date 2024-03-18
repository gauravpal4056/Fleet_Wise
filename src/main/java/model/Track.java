package model;

import java.util.Objects;

public class Track {
	private int trackId;
	//one to one
    private Trip trip;
    private String trackDate;
    private String trackTime;	
    private String remarks;
    private String details;
	public Track() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Track(int trackId, Trip trip, String trackDate, String trackTime, String remarks, String details) {
		super();
		this.trackId = trackId;
		this.trip = trip;
		this.trackDate = trackDate;
		this.trackTime = trackTime;
		this.remarks = remarks;
		this.details = details;
	}
	public int getTrackId() {
		return trackId;
	}
	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}
	public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	public String getTrackDate() {
		return trackDate;
	}
	public void setTrackDate(String trackDate) {
		this.trackDate = trackDate;
	}
	public String getTrackTime() {
		return trackTime;
	}
	public void setTrackTime(String trackTime) {
		this.trackTime = trackTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "Track [trackId=" + trackId + ", trip=" + trip + ", trackDate=" + trackDate + ", trackTime=" + trackTime
				+ ", remarks=" + remarks + ", details=" + details + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(details, remarks, trackDate, trackId, trackTime, trip);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Track other = (Track) obj;
		return Objects.equals(details, other.details) && Objects.equals(remarks, other.remarks)
				&& Objects.equals(trackDate, other.trackDate) && trackId == other.trackId
				&& Objects.equals(trackTime, other.trackTime) && Objects.equals(trip, other.trip);
	}
    
    
}

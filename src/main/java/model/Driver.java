package model;

import java.util.Date;
import java.util.Objects;

public class Driver {
	private int driverId;
    private String name;
    private String gender;
    private int age;
    private String licenceNo;
    private long phoneNumber;
    private String emailAddress;
    private String address;
    private Date joiningDate; 
    private String available;
	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Driver(int driverId, String name, String gender, int age, String licenceNo, long phoneNumber,
			String emailAddress, String address, Date joiningDate, String available) {
		super();
		this.driverId = driverId;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.licenceNo = licenceNo;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.address = address;
		this.joiningDate = joiningDate;
		this.available = available;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getLicenceNo() {
		return licenceNo;
	}
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", name=" + name + ", gender=" + gender + ", age=" + age
				+ ", licenceNo=" + licenceNo + ", phoneNumber=" + phoneNumber + ", emailAddress=" + emailAddress
				+ ", address=" + address + ", joiningDate=" + joiningDate + ", available=" + available + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, age, available, driverId, emailAddress, gender, joiningDate, licenceNo, name,
				phoneNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		return Objects.equals(address, other.address) && age == other.age && Objects.equals(available, other.available)
				&& driverId == other.driverId && Objects.equals(emailAddress, other.emailAddress)
				&& Objects.equals(gender, other.gender) && Objects.equals(joiningDate, other.joiningDate)
				&& Objects.equals(licenceNo, other.licenceNo) && Objects.equals(name, other.name)
				&& phoneNumber == other.phoneNumber;
	}
	
	
	
    
}

package model;

import java.util.Objects;

public class Admin {
	private int adminId;
    private String name;
    private String gender;
    private long phoneNumber;
    private String emailAddress;
    private String password;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(int adminId, String name, String gender, long phoneNumber, String emailAddress, String password) {
		super();
		this.adminId = adminId;
		this.name = name;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.password = password;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", name=" + name + ", gender=" + gender + ", phoneNumber=" + phoneNumber
				+ ", emailAddress=" + emailAddress + ", password=" + password + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(adminId, emailAddress, gender, name, password, phoneNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return adminId == other.adminId && Objects.equals(emailAddress, other.emailAddress)
				&& Objects.equals(gender, other.gender) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && phoneNumber == other.phoneNumber;
	}
    
}

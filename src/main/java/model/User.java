package model;

import java.util.Objects;

public class User {
	private int userId;
    private String username;
    private String password;
    private String idType;

    // Constructors
    public User() {
    }

	public User(int userId, String username, String password, String idType) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.idType = idType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", idType=" + idType
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idType, password, userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(idType, other.idType) && Objects.equals(password, other.password)
				&& userId == other.userId && Objects.equals(username, other.username);
	}
    
}

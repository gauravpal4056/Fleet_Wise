package dao;
 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Admin;
import utils.DBConnection;

public class AdminDao implements IDao<Admin> {

	private DBConnection dbConnection;

    public AdminDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Admin create(Admin admin) throws Exception {
    	Connection connection = dbConnection.getConnection();
    	String query = "INSERT INTO Admins (Name, Gender, Phone_Number, Email_Address, Password) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, admin.getName());
            statement.setString(2, admin.getGender());
            statement.setLong(3, admin.getPhoneNumber());
            statement.setString(4, admin.getEmailAddress());
            statement.setString(5, admin.getPassword());

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    admin.setAdminId(generatedKeys.getInt(1));
                } else {
                    throw new RuntimeException("Creating admin failed, no ID obtained.");
                }
            }
        }
        return admin;
    }

    @Override
    public boolean update(int id, Admin admin) throws Exception {
        String query = "UPDATE Admins SET Name=?, Gender=?, Phone_Number=?, Email_Address=?, Password=? WHERE Admin_ID=?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getName());
            statement.setString(2, admin.getGender());
            statement.setLong(3, admin.getPhoneNumber());
            statement.setString(4, admin.getEmailAddress());
            statement.setString(5, admin.getPassword());
            statement.setInt(6, id);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String query = "DELETE FROM Admins WHERE Admin_ID=?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    @Override
    public Admin findOne(int id) throws Exception {
        String query = "SELECT * FROM Admins WHERE Admin_ID=?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAdmin(resultSet);
                }
            }
        }
        return null;
    }
    
    public Admin findByNamePassword(String name, String password) throws Exception {
        String query = "SELECT * FROM Admins WHERE name=? AND password =?";
        try (Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setString(1,name );
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAdmin(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public List<Admin> findAll() throws Exception {
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM Admins";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                admins.add(mapResultSetToAdmin(resultSet));
            }
        }
        return admins;
    }

    private Admin mapResultSetToAdmin(ResultSet resultSet) throws Exception {
        Admin admin = new Admin();
        admin.setAdminId(resultSet.getInt("Admin_ID"));
        admin.setName(resultSet.getString("Name"));
        admin.setGender(resultSet.getString("Gender"));
        admin.setPhoneNumber(resultSet.getLong("Phone_Number"));
        admin.setEmailAddress(resultSet.getString("Email_Address"));
        admin.setPassword(resultSet.getString("Password"));
        return admin;
    }
}

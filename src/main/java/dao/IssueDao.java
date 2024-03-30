package dao;

import model.Driver;
import model.Issue;
import utils.DBConnection;
import utils.DateHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssueDao implements IDao<Issue> {

	private DBConnection dbConnection;
	
	public IssueDao() {
		super();
	}

	public IssueDao(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}
    @Override
    public Issue create(Issue issue) throws SQLException {
        String sql = "INSERT INTO Issues (Driver_ID, Vehicle_ID, Consignment_ID, Description, Raised_On, Resolved_On, Remarks, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection=dbConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            // Set values for parameters
            statement.setInt(1, issue.getDriver().getDriverId());
            statement.setInt(2, issue.getVehicle().getVehicleId());
            statement.setInt(3, issue.getConsignment().getConsignmentId());
            statement.setString(4, issue.getDescription());
            statement.setTimestamp(5, DateHandler.javaToSqlTime(issue.getRaisedOn()));
            statement.setTimestamp(6, DateHandler.javaToSqlTime(issue.getResolvedOn()));
            statement.setString(7, issue.getRemarks());
            statement.setString(8, issue.getStatus());
            
            // Execute the query
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating issue failed, no rows affected.");
            }

            // Get the generated keys
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    issue.setIssueId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating issue failed, no ID obtained.");
                }
            }
        }
        return issue;
    }

    @Override
    public boolean update(int id, Issue issue) throws SQLException {
		Connection connection=dbConnection.getConnection();

    	String sql = "UPDATE Issues SET Driver_ID=?, Vehicle_ID=?, Consignment_ID=?, Description=?, Raised_On=?, Resolved_On=?, Remarks=?, Status=? WHERE Issue_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set values for parameters
            statement.setInt(1, issue.getDriver().getDriverId());
            statement.setInt(2, issue.getVehicle().getVehicleId());
            statement.setInt(3, issue.getConsignment().getConsignmentId());
            statement.setString(4, issue.getDescription());
            statement.setTimestamp(5, DateHandler.javaToSqlTime(issue.getRaisedOn()));
            statement.setTimestamp(6, DateHandler.javaToSqlTime(issue.getResolvedOn()));
            statement.setString(7, issue.getRemarks());
            statement.setString(8, issue.getStatus());
            statement.setInt(9, id);

            // Execute the update
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
		Connection connection=dbConnection.getConnection();

    	String sql = "DELETE FROM Issues WHERE Issue_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public Issue findOne(int id) throws SQLException {
		Connection connection=dbConnection.getConnection();

    	String sql = "SELECT * FROM Issues WHERE Issue_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToIssue(resultSet);
                }
            }
        }
        return null; // Issue not found
    }

    @Override
    public List<Issue> findAll() throws SQLException {
		Connection connection=dbConnection.getConnection();

    	List<Issue> issues = new ArrayList<>();
        String sql = "SELECT * FROM Issues";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Issue issue = mapResultSetToIssue(resultSet);
                issues.add(issue);
            }
        }
        return issues;
    }

    // Helper method to map ResultSet to Issue object
    private Issue mapResultSetToIssue(ResultSet resultSet) throws SQLException {
        Issue issue = new Issue();
        DriverDao driverDao = new DriverDao(dbConnection);
        VehicleDao vehicleDao = new VehicleDao(dbConnection);
        ConsignmentDao consignmentDao = new ConsignmentDao(dbConnection);
        issue.setIssueId(resultSet.getInt("Issue_ID"));
        // Assuming you have appropriate DAOs to fetch related entities (Driver, Vehicle, Consignment)
        issue.setDriver(driverDao.findOne(resultSet.getInt("Driver_ID")));
        issue.setVehicle(vehicleDao.findOne(resultSet.getInt("Vehicle_ID")));
        issue.setConsignment(consignmentDao .findOne(resultSet.getInt("Consignment_ID")));
        issue.setDescription(resultSet.getString("Description"));
        issue.setRaisedOn(DateHandler.sqlTimeToJava(resultSet.getTimestamp("Raised_On")));
        issue.setResolvedOn(DateHandler.sqlTimeToJava(resultSet.getTimestamp("Resolved_On")));
        issue.setRemarks(resultSet.getString("Remarks"));
        issue.setStatus(resultSet.getString("Status"));
        return issue;
    }
}

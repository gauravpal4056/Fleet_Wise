package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Consignment;
import model.Driver;
import model.Issue;
import model.Vehicle;
import utils.DBConnection;

public class IssueDao {
    private DBConnection dbConnection;

    public IssueDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addVehicleIssue(Issue issue) throws SQLException, ClassNotFoundException {
    	String query = "INSERT INTO Issues (Vehicle_ID,  Driver_ID, Description,  Remarks) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = DBConnection.getDbConnnection().getConnection().prepareStatement(query)) {
            statement.setInt(1, issue.getVehicle().getVehicleId());
            statement.setInt(2, issue.getDriver().getDriverId());
            statement.setString(3, issue.getDescription());
            statement.setString(4, issue.getRemarks());

            statement.executeUpdate();
            
        }
    }
    
    public void addConsignmentIssue(Issue issue) throws SQLException, ClassNotFoundException {
    	String query = "INSERT INTO Issues (driver_ID,  Consignment_ID, Description,  Remarks) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = DBConnection.getDbConnnection().getConnection().prepareStatement(query)) {
            statement.setInt(1, issue.getDriver().getDriverId());
        	statement.setInt(2, issue.getConsignment().getConsignmentId());
            statement.setString(3, issue.getDescription());
            statement.setString(4, issue.getRemarks());

            statement.executeUpdate();
            
        }
    }

    public List<Issue> getAllIssues() throws SQLException, ClassNotFoundException {
        List<Issue> issues = new ArrayList<>();
        String query = "SELECT * FROM Issues";
        try (Statement statement = DBConnection.getDbConnnection().getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Issue issue = extractIssueFromResultSet(resultSet);
                issues.add(issue);
            }
        }
        return issues;
    }

    public Issue getIssueById(int issueId) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM Issues WHERE Issue_ID = ?";
        
        try (PreparedStatement statement = DBConnection.getDbConnnection().getConnection().prepareStatement(query)) {
            statement.setInt(1, issueId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractIssueFromResultSet(resultSet);
                }
            }
        }
        return null;
    }
    
    public List<Issue> getAllIssueByDriver(int driverId) throws SQLException {
        List<Issue> issues = new ArrayList<>();
        String query = "SELECT * FROM Issues where driver_id=?";
        
        try {
        	PreparedStatement statement = DBConnection.getDbConnnection().getConnection().prepareStatement(query);
        	statement.setInt(1, driverId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Issue issue = extractIssueFromResultSet(resultSet);
                issues.add(issue);
            }
        }
        catch(Exception e){
        	System.out.println(e);
        }
        return issues;
    }
    
    public boolean solveIssue(int issueId) {
    	boolean res= true;
    	
    	String query = "UPDATE Issues SET status='CLOSED' where issue_id= ? ";
        try (PreparedStatement statement = DBConnection.getDbConnnection().getConnection().prepareStatement(query)) {
            statement.setInt(1, issueId);

            statement.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return res;
    }
    
    private Issue extractIssueFromResultSet(ResultSet resultSet) throws SQLException {
        Issue issue = new Issue();
        issue.setIssueId(resultSet.getInt("Issue_ID"));
        issue.setDescription(resultSet.getString("Description"));
        issue.setRaisedOn(resultSet.getTimestamp("Raised_On"));
        issue.setResolvedOn(resultSet.getTimestamp("Resolved_On"));
        issue.setRemarks(resultSet.getString("Remarks"));
        issue.setStatus(resultSet.getString("Status"));
        Consignment c  =null;
    	ConsignmentDao cDao = new ConsignmentDao(dbConnection);
        c = cDao.findOne(resultSet.getInt("Consignment_ID"));
        issue.setConsignment(c);

        DriverDao dDao = new DriverDao(dbConnection); 
        Driver d = dDao.findOne(resultSet.getInt("Driver_ID"));
        issue.setDriver(d);
        System.out.println(issue);
        return issue;
    }
}

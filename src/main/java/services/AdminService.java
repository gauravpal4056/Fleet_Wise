package services;

import java.sql.SQLException;
import java.util.List;

import dao.ConsignmentDao;
import dao.IssueDao;
import model.Consignment;
import utils.DBConnection;

public class AdminService {

	 private DBConnection dbConnection;

	    public AdminService(DBConnection dbConnection) {
	        this.dbConnection = dbConnection;
	    }
	
	    public boolean scheduleAllConsignments(int vehicleid, int routeId) throws SQLException {
	    	boolean res = true;
	    	ConsignmentDao cDao = new ConsignmentDao(dbConnection);
	    	List<Consignment> cs =cDao.getConsignmntsByRouteId(routeId);
	    	for(Consignment c: cs) {
	    		res=res&&cDao.scheduleOneConsignment(vehicleid, c.getConsignmentId(), routeId);
	    	}
	    	
	    	return res;
	    }
	    
	    public boolean solveIssue(int issueId) {
	    	IssueDao iDao = new IssueDao(dbConnection);
	    	return iDao.solveIssue(issueId);
	    }
	    
	   
	    
	
	
}

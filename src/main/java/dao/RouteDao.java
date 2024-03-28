
package dao;

import model.Route;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDao implements IDao<Route> {

    private DBConnection dbConnection;

    public RouteDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Route create(Route route) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnection.getConnection();
            String query = "INSERT INTO routes (route_Name, start_Point, destination_Point) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, route.getRouteName());
            statement.setString(2, route.getStartPoint());
            statement.setString(3, route.getDestinationPoint());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating route failed, no rows affected.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace(); 	
            throw e;
        }
        return route;
    }

    @Override
    public boolean update(int id, Route route) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnection.getConnection();
            String query = "UPDATE routes SET route_Name=?, start_Point=?, destination_Point=? WHERE route_Id=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, route.getRouteName());
            statement.setString(2, route.getStartPoint());
            statement.setString(3, route.getDestinationPoint());
            statement.setInt(4, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnection.getConnection();
            String query = "DELETE FROM routes WHERE route_Id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
    }

    @Override
    public Route findOne(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Route route = null;
        try {
            connection = dbConnection.getConnection();
            String query = "SELECT * FROM routes WHERE route_Id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                route = extractRouteFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return route;
    }

    @Override
    public List<Route> findAll() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Route> routes = new ArrayList<>();
        try {
            connection = dbConnection.getConnection();
            String query = "SELECT * FROM routes";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Route route = extractRouteFromResultSet(resultSet);
                routes.add(route);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        }
        return routes;
    }

    private Route extractRouteFromResultSet(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        route.setRouteId(resultSet.getInt("route_Id"));
        route.setRouteName(resultSet.getString("route_Name"));
        route.setStartPoint(resultSet.getString("start_Point"));
        route.setDestinationPoint(resultSet.getString("destination_Point"));
        return route;
    }
}

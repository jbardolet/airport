package com.solvd.airport.db.dao.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.IDAO;
import com.solvd.airport.db.dao.ITripDAO;
import com.solvd.airport.db.dao.model.Seat;
import com.solvd.airport.db.dao.model.Trip;
import com.solvd.airport.db.service.impl.TripUtilsImpl;
import com.solvd.airport.db.utils.mysql.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TripDAO implements ITripDAO {
    private static final Logger logger = LogManager.getLogger("TripDAO");
    private static final String INSERT = "INSERT INTO Trips (id, id_Airplane, id_from, id_to, time_departure) VALUES (?,?, ?,?,?)";
    private static final String SELECT_BY_ID = "SELECT * FROM Trips WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM Trips";
    private static final String DELETE = "DELETE FROM Trips WHERE id = ?";
    private TripUtilsImpl tripUtils = new TripUtilsImpl();

    @Override
    public void insert(Trip trip) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT);
            statement.setLong(1,trip.getId());
            statement.setLong(2,trip.getAirplane().getId());
            statement.setLong(3, trip.getCityFrom().getId());
            statement.setLong(4, trip.getCityTo().getId());
            statement.setDate(5, (Date) trip.getDepartureDate());
            statement.executeUpdate();
            logger.info("Record created");

        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ INSERT);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statement");
            }
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public Trip getById(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Trip trip = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
            resultSet = statement.executeQuery();
            trip = fillTripByResultSet(resultSet);

        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ SELECT_BY_ID);
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statements");
            }
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return trip;
    }

    @Override
    public Trip getTripByCrewServiceId(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Trip trip = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement("SELECT * FROM Trips WHERE id = (SELECT Trips_id FROM CrewServices WHERE id=?)");
            statement.setLong(1, id);
            statement.executeUpdate();
            resultSet = statement.executeQuery();
            trip = fillTripByResultSet(resultSet);

        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: SELECT * FROM Trips WHERE id = (SELECT Trips_id FROM CrewServices WHERE id=?)");
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statements");
            }
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return trip;
    }

    @Override
    public List<Trip> getAll() throws DataConectionExeption {
        List<Trip> trips = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                trips.add(fillTripByResultSet(resultSet));
            }

        } catch (SQLException |InterruptedException e) {
            throw new DataConectionExeption("Error query: " + SELECT_ALL);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statements");
            }

            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }

        return trips;
    }

    @Override
    public void deleteById(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            logger.info("Record deleted");


        } catch (SQLException | InterruptedException e) {
            throw new DataConectionExeption("Error query: "+ DELETE);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statements");
            }
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
    }

    private Trip fillTripByResultSet(ResultSet resultSet) throws DataConectionExeption {
        Trip trip= null;
        try {
            trip= new Trip();
            trip.setId(resultSet.getLong(1));
           // trip.setAirplane(tripUtils.getAirplaneById(resultSet.getLong(2)));
           // trip.setCityFrom(tripUtils.getCityById(resultSet.getLong(3)));
           // trip.setCityTo(tripUtils.getCityById(resultSet.getLong(4)));
            trip.setDepartureDate(resultSet.getDate(5));
        } catch (SQLException e) {
            throw new DataConectionExeption("Error filling role object");
        }

        return trip;
    }


}

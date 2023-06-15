package com.solvd.airport.db.dao.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.IDAO;
import com.solvd.airport.db.dao.model.CrewService;
import com.solvd.airport.db.dao.model.Role;
import com.solvd.airport.db.dao.model.Seat;
import com.solvd.airport.db.service.impl.CrewServiceUtilsImpl;
import com.solvd.airport.db.utils.mysql.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrewServiceDAO implements IDAO<CrewService> {

    private static final Logger logger = LogManager.getLogger("CrewServiceDAO");
    private static final String INSERT = "INSERT INTO Crew_Services (id, People_id, Trips_id) VALUES (?,?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM Crew_Services WHERE id = ?";
      private static final String SELECT_ALL = "SELECT * FROM Crew_Services";
    private static final String DELETE = "DELETE FROM Crew_Services WHERE id = ?";
    private CrewServiceUtilsImpl crewServiceUtils = new CrewServiceUtilsImpl();
    @Override
    public void insert(CrewService crewService) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT);
            statement.setLong(1,crewService.getId());
            statement.setLong(2, crewService.getPerson().getId());
            statement.setLong(3, crewService.getTrip().getId());
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
    public CrewService getById(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        CrewService crewService = null;
        ResultSet resultSet =null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
            resultSet = statement.executeQuery();
            crewService = fillCSByResultSet(resultSet);

        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ SELECT_BY_ID);
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statement");
            }
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return crewService;
    }

    @Override
    public List<CrewService> getAll() throws DataConectionExeption {
        List<CrewService> crewServices = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                crewServices.add(fillCSByResultSet(resultSet));
            }

        } catch (SQLException |InterruptedException e) {
            throw new DataConectionExeption("Error query: " + SELECT_ALL);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statement");
            }
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }

        return crewServices;
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
                throw new DataConectionExeption("Error closing statement");
            }
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
    }

    private CrewService fillCSByResultSet(ResultSet resultSet) throws DataConectionExeption {
        CrewService crewService= null;
        try {
            crewService= new CrewService();
            crewService.setId(resultSet.getLong(1));
           // crewService.setPerson(crewServiceUtils.getPersonById(resultSet.getLong(2)));
           // crewService.setTrip(crewServiceUtils.getTripById(resultSet.getLong(3)));
        } catch (SQLException e) {
            throw new DataConectionExeption("Error filling role object");
        }

        return crewService;
    }
}

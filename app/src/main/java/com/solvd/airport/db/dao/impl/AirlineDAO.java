package com.solvd.airport.db.dao.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.IDAO;
import com.solvd.airport.db.dao.model.Airline;
import com.solvd.airport.db.dao.model.Role;
import com.solvd.airport.db.utils.mysql.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirlineDAO implements IDAO<Airline> {
    private static final Logger logger = LogManager.getLogger("AirlineDAO");
    private static final String SELECT_ALL = "SELECT * FROM airlines";
    private static final String INSERT = "INSERT INTO airlines (id, name) VALUES (?,?)";
    private static final String SELECT_BY_ID = "SELECT * FROM airlines WHERE id = ?";

    private static final String DELETE = "DELETE FROM airlines WHERE id = ?";

    @Override
    public void insert(Airline airline) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT);
            statement.setLong(1,airline.getId());
            statement.setString(2, airline.getName());
            statement.executeUpdate();
            logger.info("Record created");
            statement.close();
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
    public Airline getById(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        Airline airline;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
            resultSet = statement.executeQuery();
            airline = fillAirlineByResultSet(resultSet);

        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ SELECT_BY_ID);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statement");
            }
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return airline;
    }

    @Override
    public List<Airline> getAll() throws DataConectionExeption {
        List<Airline> airlines = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                airlines.add(fillAirlineByResultSet(resultSet));
            }

        } catch (SQLException | InterruptedException e) {
            throw new DataConectionExeption("Error query: "+SELECT_ALL);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statement");
            }

            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return airlines;
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

    private Airline fillAirlineByResultSet(ResultSet resultSet) throws DataConectionExeption {
        Airline airline= null;
        try {
            airline= new Airline();
            airline.setId(resultSet.getLong(1));
            airline.setName(resultSet.getString(2));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return airline;
    }

}

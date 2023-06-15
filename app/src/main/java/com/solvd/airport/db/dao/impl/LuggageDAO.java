package com.solvd.airport.db.dao.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.IDAO;
import com.solvd.airport.db.dao.model.Food;
import com.solvd.airport.db.dao.model.Luggage;
import com.solvd.airport.db.utils.mysql.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LuggageDAO implements IDAO<Luggage> {

    private static final Logger logger = LogManager.getLogger("LuggageDAO");
    private static final String INSERT = "INSERT INTO luggage (id, price, description) VALUES (?,?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM roles WHERE id = ?";

    private static final String SELECT_ALL = "SELECT * FROM roles";
    private static final String DELETE = "DELETE FROM roles WHERE id = ?";

    @Override
    public void insert(Luggage luggage) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT);
            statement.setLong(1,luggage.getId());
            statement.setFloat(2, luggage.getPrice());
            statement.setString(3, luggage.getDescription());
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
    public Luggage getById(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        Luggage luggage = null;
        ResultSet resultSet =null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
            resultSet = statement.executeQuery();
            luggage = fillLuggageByResultSet(resultSet);


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
        return luggage;
    }

    @Override
    public List<Luggage> getAll() throws DataConectionExeption {
        List<Luggage> luggageList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_ALL);
            statement.executeUpdate();
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                luggageList.add(fillLuggageByResultSet(resultSet));
            }


        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ SELECT_ALL);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statement");
            }
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return luggageList;
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

    private Luggage fillLuggageByResultSet(ResultSet resultSet) throws DataConectionExeption {
        Luggage luggage= null;
        try {
            luggage= new Luggage();
            luggage.setId(resultSet.getLong(1));
            luggage.setPrice(resultSet.getFloat(2));
            luggage.setDescription(resultSet.getString(3));
        } catch (SQLException e) {
            throw new DataConectionExeption("Error filling luggage object");
        }

        return luggage;
    }
}

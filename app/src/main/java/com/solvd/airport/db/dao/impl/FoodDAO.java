package com.solvd.airport.db.dao.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.IDAO;
import com.solvd.airport.db.dao.model.Food;
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

public class FoodDAO implements IDAO<Food> {

    private static final Logger logger = LogManager.getLogger("FoodDAO");
    private static final String INSERT = "INSERT INTO food (id, name, description) VALUES (?,?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM food WHERE id = ?";

    private static final String SELECT_ALL = "SELECT * FROM food";
    private static final String DELETE = "DELETE FROM food WHERE id = ?";

    @Override
    public void insert(Food food) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT);
            statement.setLong(1,food.getId());
            statement.setString(2, food.getName());
            statement.setString(3, food.getDescription());
            statement.executeUpdate();
            logger.info("Record created");
            statement.close();
        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ INSERT);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }

    }

    @Override
    public Food getById(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        Food food = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
            ResultSet resultSet = statement.executeQuery();
            food = fillFoodByResultSet(resultSet);
            resultSet.close();
        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ SELECT_BY_ID);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return food;
    }

    @Override
    public List<Food> getAll() throws DataConectionExeption {
        List<Food> foods = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_ALL);
            statement.executeUpdate();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                foods.add(fillFoodByResultSet(resultSet));
            }
            resultSet.close();
            statement.close();

        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ SELECT_ALL);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return foods;
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
            preparedStatement.close();
        } catch (SQLException | InterruptedException e) {
            throw new DataConectionExeption("Error query: "+ DELETE);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
    }

    private Food fillFoodByResultSet(ResultSet resultSet) throws DataConectionExeption {
        Food food= null;
        try {
            food= new Food();
            food.setId(resultSet.getLong(1));
            food.setName(resultSet.getString(2));
            food.setDescription(resultSet.getString(3));
        } catch (SQLException e) {
            throw new DataConectionExeption("Error filling food object");
        }

        return food;
    }
}

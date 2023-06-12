package com.solvd.airport.db.dao.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.ICityDAO;
import com.solvd.airport.db.dao.model.City;
import com.solvd.airport.db.utils.mysql.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAO implements ICityDAO {
    private static final Logger logger = LogManager.getLogger("CityDAO");

    private static final String INSERT = "INSERT INTO cities (id, City_Name, Country, State) VALUES (?,?, ?, ?)";
    private static final String SELECT_BY_STATE = "SELECT * FROM cities WHERE State = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM cities WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM cities";
    private static final String DELETE = "DELETE FROM cities WHERE id = ?";

    @Override
    public List<City> selectByState(String state) throws DataConectionExeption {
        List<City> cityList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_STATE);
            preparedStatement.setString(1, state);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                City city= fillCityByResultSet(resultSet);
                cityList.add(city);
            }
        } catch (SQLException |InterruptedException e) {
            throw new DataConectionExeption("Error query: "+ SELECT_BY_STATE);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }

        return cityList;

    }

    @Override
    public void insert(City city) throws DataConectionExeption  {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT);
            statement.setInt(1, Math.toIntExact(city.getId()));
            statement.setString(2, city.getName());
            statement.setString(3, city.getCountry());
            statement.setString(4, city.getState());
            statement.executeUpdate();
            logger.info("Record created");
        } catch (SQLException |InterruptedException e) {
            throw new DataConectionExeption("Error query: "+ INSERT);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public City getById(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        City city = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            city = fillCityByResultSet(resultSet);
        } catch (SQLException | InterruptedException e) {
            throw new DataConectionExeption("Error query: "+ SELECT_BY_ID);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }

        return city;
    }

    @Override
    public List<City> getAll() throws DataConectionExeption {
        List<City> cityList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                City city= fillCityByResultSet(resultSet);
                cityList.add(city);
            }
        } catch (SQLException |InterruptedException e) {
            throw new DataConectionExeption("Error query: "+ SELECT_ALL);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }

        return cityList;
    }

    @Override
    public void deleteById(Long id) throws DataConectionExeption{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, Math.toIntExact(id));
            preparedStatement.executeUpdate();
            logger.info("Record deleted");
        } catch (SQLException | InterruptedException e) {
            throw new DataConectionExeption("Error query: "+ DELETE);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }


    }
    private City fillCityByResultSet(ResultSet resultSet) throws DataConectionExeption {
        City city= new City();
        try {
            city.setId(resultSet.getLong(1));
            city.setName(resultSet.getNString(2));
            city.setCountry(resultSet.getString(3));
            city.setState(resultSet.getString(4));
        } catch (SQLException e) {
            throw new DataConectionExeption("Error filling city object");
        }

        return city;
    }



}

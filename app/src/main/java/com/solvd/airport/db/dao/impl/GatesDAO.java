package com.solvd.airport.db.dao.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.IDAO;
import com.solvd.airport.db.dao.model.Gate;
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

public class GatesDAO implements IDAO<Gate> {

    private static final Logger logger = LogManager.getLogger("GatesDAO");
    private static final String INSERT = "INSERT INTO gates (id, number) VALUES (?,?)";
    private static final String SELECT_BY_ID = "SELECT * FROM gates WHERE id = ?";

    private static final String SELECT_ALL = "SELECT * FROM gates";
    private static final String DELETE = "DELETE FROM gates WHERE id = ?";
    @Override
    public void insert(Gate gate) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT);
            statement.setLong(1,gate.getId());
            statement.setInt(2, gate.getNumber());
            statement.executeUpdate();
            logger.info("Record created");
        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ INSERT);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing PreparedStatement");
            }
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }

    }

    @Override
    public Gate getById(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement;
        Gate gate = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
            ResultSet resultSet = statement.executeQuery();
            gate = fillGateByResultSet(resultSet);

        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ SELECT_BY_ID);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return gate;
    }

    @Override
    public List<Gate> getAll() throws DataConectionExeption {
        List<Gate> gates = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_ALL);
            statement.executeUpdate();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                gates.add(fillGateByResultSet(resultSet));
            }

        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ SELECT_ALL);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing PreparedStatement");
            }
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return gates;
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
                throw new DataConectionExeption("Error closing PreparedStatement");
            }
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
    }

    private Gate fillGateByResultSet(ResultSet resultSet) throws DataConectionExeption {
        Gate gate= null;
        try {
            gate= new Gate();
            gate.setId(resultSet.getLong(1));
            gate.setNumber(resultSet.getInt(2));
        } catch (SQLException e) {
            throw new DataConectionExeption("Error filling role object");
        }

        return gate;
    }
}

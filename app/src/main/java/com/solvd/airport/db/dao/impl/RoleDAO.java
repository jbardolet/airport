package com.solvd.airport.db.dao.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.IDAO;
import com.solvd.airport.db.dao.IRoleDAO;
import com.solvd.airport.db.dao.model.City;
import com.solvd.airport.db.dao.model.Role;
import com.solvd.airport.db.utils.mysql.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO implements IRoleDAO {
    private static final Logger logger = LogManager.getLogger("RoleDAO");
    private static final String INSERT = "INSERT INTO roles (id, role_name, description) VALUES (?,?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM roles WHERE id = ?";

    private static final String SELECT_ALL = "SELECT * FROM roles";
    private static final String DELETE = "DELETE FROM roles WHERE id = ?";

    @Override
    public void insert(Role role) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT);
            statement.setLong(1,role.getId());
            statement.setString(2, role.getName());
            statement.setString(3, role.getDescription());
            statement.executeUpdate();
            logger.info("Record created");
        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ INSERT);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }

    }

    @Override
    public Role getById(Long id) throws DataConectionExeption {

        Connection connection = null;
        PreparedStatement statement = null;
        Role role = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
            ResultSet resultSet = statement.executeQuery();
            role = fillRoleByResultSet(resultSet);

        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ SELECT_BY_ID);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return role;
    }

    @Override
    public List<Role> getAll() throws DataConectionExeption {
        List<Role> roles = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_ALL);
            statement.executeUpdate();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Role role = fillRoleByResultSet(resultSet);
                roles.add(role);
            }

        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ SELECT_ALL);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return roles;
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
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
    }

    private Role fillRoleByResultSet(ResultSet resultSet) throws DataConectionExeption {
        Role role= null;
        try {
            role= new Role();
            role.setId(resultSet.getLong(1));
            role.setName(resultSet.getString(2));
            role.setDescription(resultSet.getString(3));
        } catch (SQLException e) {
            throw new DataConectionExeption("Error filling role object");
        }

        return role;
    }


}

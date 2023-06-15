package com.solvd.airport.db.dao.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.IDAO;
import com.solvd.airport.db.dao.IPilotLicenseDAO;
import com.solvd.airport.db.dao.model.PilotLicense;
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

public class PilotLicenseDAO implements IPilotLicenseDAO {
    private static final Logger logger = LogManager.getLogger("PilotLicenseDAO");

    private static final String INSERT = "INSERT INTO Pilot_license (id, pilot_license) VALUES (?,?)";
    private static final String SELECT_BY_ID = "SELECT * FROM Pilot_license WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM Pilot_license";
    private static final String DELETE = "DELETE FROM Pilot_license WHERE id = ?";
    @Override
    public void insert(PilotLicense pilotLicense) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT);
            statement.setLong(1,pilotLicense.getId());
            statement.setString(2, pilotLicense.getLicenseCode());
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
    public PilotLicense getById(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement=null;
        PilotLicense pilotLicense ;
        ResultSet resultSet=null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
            resultSet = statement.executeQuery();
            pilotLicense = fillRoleByResultSet(resultSet);


        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ SELECT_BY_ID);
        } finally {

            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statements");
            }
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return pilotLicense;
    }
    @Override
    public PilotLicense getLicenseByPersonId(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement=null;
        PilotLicense pilotLicense ;
        ResultSet resultSet=null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement("SELECT * FROM Pilot_license WHERE ID = (SELECT Pilot_license_id FROM People WHERE id = ?)");
            statement.setLong(1, id);
            statement.executeUpdate();
            resultSet = statement.executeQuery();
            pilotLicense = fillRoleByResultSet(resultSet);


        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: SELECT * FROM Pilot_license WHERE ID = (SELECT Pilot_license_id FROM People WHERE id = ?)");
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statements");
            }
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return pilotLicense;
    }
    @Override
    public List<PilotLicense> getAll() throws DataConectionExeption {
        List<PilotLicense> pilotLicenses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_ALL);
            statement.executeUpdate();
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                PilotLicense pilotLicense = fillRoleByResultSet(resultSet);
                pilotLicenses.add(pilotLicense);
            }


        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ SELECT_ALL);
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statements");
            }
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return pilotLicenses;
    }

    @Override
    public void deleteById(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement preparedStatement=null;
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

    private PilotLicense fillRoleByResultSet(ResultSet resultSet) throws DataConectionExeption {
        PilotLicense pilotLicense= null;
        try {
            pilotLicense= new PilotLicense();
            pilotLicense.setId(resultSet.getLong(1));
            pilotLicense.setLicenseCode(resultSet.getString(2));
        } catch (SQLException e) {
            throw new DataConectionExeption("Error filling pilotLicense object");
        }

        return pilotLicense;
    }


}

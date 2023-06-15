package com.solvd.airport.db.dao.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.IAirplaneDAO;
import com.solvd.airport.db.dao.IDAO;
import com.solvd.airport.db.dao.model.Airline;
import com.solvd.airport.db.dao.model.Airplane;
import com.solvd.airport.db.service.impl.AirplaneUtilsImpl;
import com.solvd.airport.db.utils.mysql.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirplaneDAO implements IAirplaneDAO {

    private static final Logger logger = LogManager.getLogger("AirplaneDAO");
    private static final String SELECT_ALL = "SELECT * FROM airplanes";
    private static final String SELECT_BY_ID = "SELECT * FROM airplanes WHERE id=?";
    private static final String INSERT = "INSERT INTO airplanes (id, name, id_airlines, Gates_id) VALUES (?,?,?,?)";
    private static final String DELETE = "SELECT * FROM airlines";

   // private AirplaneUtilsImpl airplaneUtils = new AirplaneUtilsImpl();



    @Override
    public Airplane getById(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Airplane airplane = null;
        ResultSet resultSet =null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1,id);
            resultSet = preparedStatement.executeQuery();
            airplane = fillAirplaneByResultSet(resultSet);

        } catch (SQLException | InterruptedException e) {
            throw new DataConectionExeption("Error query: "+SELECT_BY_ID);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statements");
            }

            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return airplane;

    }
    @Override
    public Airplane getAriplaneBySeatId(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Airplane airplane = null;
        ResultSet resultSet =null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Airplanes WHERE id=(SELECT id_Airplane FORM Seats WHERE id=?)");
            preparedStatement.setLong(1,id);
            resultSet = preparedStatement.executeQuery();
            airplane = fillAirplaneByResultSet(resultSet);

        } catch (SQLException | InterruptedException e) {
            throw new DataConectionExeption("Error query: SELECT * FROM Airplanes WHERE id=(SELECT id_Airplane FORM Seats WHERE id=?)");
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statements");
            }

            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return airplane;
    }

    @Override
    public Airplane getAriplaneByTripId(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Airplane airplane = null;
        ResultSet resultSet =null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Airplanes WHERE id=(SELECT id_Airplane FORM Trips WHERE id=?)");
            preparedStatement.setLong(1,id);
            resultSet = preparedStatement.executeQuery();
            airplane = fillAirplaneByResultSet(resultSet);

        } catch (SQLException | InterruptedException e) {
            throw new DataConectionExeption("Error query: SELECT * FROM Airplanes WHERE id=(SELECT id_Airplane FORM Trips WHERE id=?)");
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statements");
            }

            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return airplane;
    }

    public List<Airplane> getAll() throws DataConectionExeption {
        List<Airplane> airplanes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                airplanes.add(fillAirplaneByResultSet(resultSet));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException |InterruptedException e) {
            throw new DataConectionExeption("Error query: " + SELECT_ALL);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }

        return airplanes;
    }

    @Override
    public void insert(Airplane airplane) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT);
            statement.setLong(1,airplane.getId());
            statement.setString(2, airplane.getName());
            statement.setLong(3,airplane.getAirline().getId());
            statement.setLong(4,airplane.getGate().getId());
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
    public void deleteById(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement preparedStatement;
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



    private Airplane fillAirplaneByResultSet(ResultSet resultSet) throws DataConectionExeption {
        Airplane airplane= null;
        try {
            airplane= new Airplane();
            airplane.setId(resultSet.getLong(1));
            airplane.setName(resultSet.getString(2));
           // airplane.setAirline(airplaneUtils.getAirlineById(resultSet.getLong(3)));
           // airplane.setGate(airplaneUtils.getGateById(resultSet.getLong(4)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return airplane;
    }


}

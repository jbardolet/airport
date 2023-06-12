package com.solvd.airport.db.dao.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.IDAO;
import com.solvd.airport.db.dao.ISeatDAO;
import com.solvd.airport.db.dao.model.Airplane;
import com.solvd.airport.db.dao.model.Role;
import com.solvd.airport.db.dao.model.Seat;
import com.solvd.airport.db.service.impl.SeatUtilsImpl;
import com.solvd.airport.db.utils.mysql.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO implements ISeatDAO {
    private static final Logger logger = LogManager.getLogger("SeatDAO");
    private static final String INSERT = "INSERT INTO Seats (id, seatNumber, seatLetter, Id_airplane, price) VALUES (?,?, ?,?,?)";
    private static final String SELECT_BY_ID = "SELECT * FROM Seats WHERE id = ?";
    private static final String SELECT_BY_PLANEID = "SELECT * FROM Seats WHERE id_airplane = ?";

    private static final String SELECT_ALL = "SELECT * FROM Seats";
    private static final String DELETE = "DELETE FROM Seats WHERE id = ?";
    private SeatUtilsImpl seatUtils = new SeatUtilsImpl();


    @Override
    public void insert(Seat seat) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT);
            statement.setLong(1,seat.getId());
            statement.setInt(2, seat.getSeatNumber());
            statement.setString(3, seat.getSeatLetter());
            statement.setLong(4,seat.getAirplane().getId());
            statement.setFloat(5,seat.getPrice());
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
    public Seat getById(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        Seat seat = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
            ResultSet resultSet = statement.executeQuery();
            seat = fillSeatByResultSet(resultSet);
            statement.close();
        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ SELECT_BY_ID);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return seat;
    }

    @Override
    public List<Seat> getAll() throws DataConectionExeption {
        List<Seat> seats = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                seats.add(fillSeatByResultSet(resultSet));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException |InterruptedException e) {
            throw new DataConectionExeption("Error query: " + SELECT_ALL);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }

        return seats;
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
    @Override
    public List<Seat> selectByAirplaneId(Long id)throws DataConectionExeption{
        List<Seat> seats = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_PLANEID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                seats.add(fillSeatByResultSet(resultSet));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException |InterruptedException e) {
            throw new DataConectionExeption("Error query: " + SELECT_BY_PLANEID);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }

        return seats;
    }
    private Seat fillSeatByResultSet(ResultSet resultSet) throws DataConectionExeption {
        Seat seat= null;
        try {
            seat= new Seat();
            seat.setId(resultSet.getLong(1));
            seat.setSeatNumber(resultSet.getInt(2));
            seat.setSeatLetter(resultSet.getString(3));
            seat.setAirplane(seatUtils.getAirplaneById(resultSet.getLong(4)));
            seat.setPrice(resultSet.getFloat(5));
        } catch (SQLException e) {
            throw new DataConectionExeption("Error filling role object");
        }

        return seat;
    }
}

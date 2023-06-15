package com.solvd.airport.db.dao.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.IDAO;
import com.solvd.airport.db.dao.model.Ticket;
import com.solvd.airport.db.utils.mysql.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements IDAO<Ticket> {

    private static final Logger logger = LogManager.getLogger("TicketDAO");
    private static final String INSERT = "INSERT INTO Tickets (id, id_passenger, id_trip, id_luggage, id_seats, Food_id) VALUES (?,?, ?,?,?,?)";
    private static final String SELECT_BY_ID = "SELECT * FROM Tickets WHERE id = ?";

    private static final String SELECT_ALL = "SELECT * FROM Tickets";
    private static final String DELETE = "DELETE FROM Tickets WHERE id = ?";

    @Override
    public void insert(Ticket ticket) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT);
            statement.setLong(1,ticket.getId());
            statement.setLong(2, ticket.getPerson().getId());
            statement.setLong(3, ticket.getTrip().getId());
            statement.setLong(4,ticket.getLuggage().getId());
            statement.setLong(5,ticket.getSeat().getId());
            statement.setLong(6,ticket.getFood().getId());
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
    public Ticket getById(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        Ticket ticket = null;
        ResultSet resultSet =null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
            resultSet = statement.executeQuery();
            ticket = fillTicketByResultSet(resultSet);

        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error query: "+ SELECT_BY_ID);
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statements");
            }

            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
        return ticket;
    }

    @Override
    public List<Ticket> getAll() throws DataConectionExeption {
        List<Ticket> tickets = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                tickets.add(fillTicketByResultSet(resultSet));
            }
        } catch (SQLException |InterruptedException e) {
            throw new DataConectionExeption("Error query: " + SELECT_ALL);
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                throw new DataConectionExeption("Error closing statements");
            }
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }

        return tickets;
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
                throw new DataConectionExeption("Error closing statements");
            }
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
    }
    private Ticket fillTicketByResultSet(ResultSet resultSet) throws DataConectionExeption {
        Ticket ticket= null;
        try {
            ticket= new Ticket();
            ticket.setId(resultSet.getLong(1));

        } catch (SQLException e) {
            throw new DataConectionExeption("Error filling role object");
        }

        return ticket;
    }
}

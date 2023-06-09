package com.solvd.airport.db.dao.impl;

import com.solvd.airport.db.dao.IPersonDAO;
import com.solvd.airport.db.dao.model.Person;
import com.solvd.airport.db.utils.mysql.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO implements IPersonDAO {
    private static final Logger logger = LogManager.getLogger("CityDAO");
    private static final String INSERT = "INSERT INTO people (id, firstName, lastName, date_birth,id_city, worker_id, start_date) VALUES (?,?, ?, ?,?,?,?)";
    private static final String SELECT_BY_ID = "SELECT * FROM cities WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM";

    @Override
    public void insert(Person person) throws SQLException, InterruptedException {
        Connection connection = ConnectionPoolImpl.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        statement.setInt(1, Math.toIntExact(person.getId()));
        statement.setString(2, person.getName());
        statement.setString(3, person.getLastName());
        statement.setDate(4, (Date) person.getDataBirth());
        statement.setLong(5,person.getCity().getId());
        statement.setLong(6, person.getWorkerId());
        statement.setDate(7, (Date) person.getStartDate());
        statement.executeUpdate();
        logger.info("Record created");

        ConnectionPoolImpl.getInstance().releaseConnection(connection);
    }

    @Override
    public Person getById(Integer id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPoolImpl.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
        preparedStatement.setInt(1, Math.toIntExact(id));
        ResultSet resultSet = preparedStatement.executeQuery();
        Person person = fillPersonByResultSet(resultSet);
        ConnectionPoolImpl.getInstance().releaseConnection(connection);
        return person;
    }

    @Override
    public List<Person> getAll() throws SQLException, InterruptedException {
        List<Person> people = new ArrayList<>();
        Connection connection = ConnectionPoolImpl.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Person person = fillPersonByResultSet(resultSet);
            people.add(person);
        }
        ConnectionPoolImpl.getInstance().releaseConnection(connection);
        return people;

    }

    @Override
    public void deletePersonById(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPoolImpl.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM people WHERE id = ?");
        preparedStatement.setInt(1, Math.toIntExact(id));
        preparedStatement.executeUpdate();
        logger.info("Record deleted");
        ConnectionPoolImpl.getInstance().releaseConnection(connection);
    }

    @Override
    public void deletePersonByObject(Person p) throws SQLException, InterruptedException {
        deletePersonById(p.getId());

    }
    private Person fillPersonByResultSet(ResultSet resultSet) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getLong(1));
        person.setName(resultSet.getString(2));
        person.setLastName(resultSet.getString(3));
        person.setDataBirth(resultSet.getDate(5));
        person.setWorkerId(resultSet.getLong(6));
        person.setStartDate(resultSet.getDate(7));
        return person;
    }
}

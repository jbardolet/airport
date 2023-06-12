package com.solvd.airport.db.dao.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.IPersonDAO;
import com.solvd.airport.db.dao.model.Person;
import com.solvd.airport.db.service.impl.PersonUtilsImpl;
import com.solvd.airport.db.utils.mysql.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO implements IPersonDAO {
    private static final Logger logger = LogManager.getLogger("CityDAO");
    private static final String INSERT = "INSERT INTO people (id, firstName, lastName, date_birth,id_city, worker_id, start_date, Roles_id, Pilot_license_id) VALUES (?,?, ?, ?,?,?,?,?,?)";
    private static final String SELECT_BY_ID = "SELECT * FROM cities WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM cities";

    private PersonUtilsImpl personUtiles;

    @Override
    public void insert(Person person) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            statement = connection.prepareStatement(INSERT);
            statement.setLong(1,person.getId());
            statement.setString(2, person.getName());
            statement.setString(3, person.getLastName());
            statement.setDate(4, (Date) person.getDataBirth());
            statement.setLong(5,person.getCity().getId());
            statement.setLong(6, person.getWorkerId());
            statement.setDate(7, (Date) person.getStartDate());
            statement.setLong(8, person.getRole().getId());
            statement.setLong(9, person.getPilotLicense().getId());
            statement.executeUpdate();
            logger.info("Record created");
        } catch (SQLException | InterruptedException e)  {
            throw new DataConectionExeption("Error INSERT Person in BBDD");
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }



    }

    @Override
    public Person getById(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Person person;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            person = fillPersonByResultSet(resultSet);
        } catch (SQLException | InterruptedException e) {
            throw new DataConectionExeption("Error query: "+SELECT_BY_ID);
        } finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }
       return person;
    }

    @Override
    public List<Person> getAll() throws DataConectionExeption {
        List<Person> people = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Person person = fillPersonByResultSet(resultSet);
                people.add(person);
            }
        }  catch (SQLException | InterruptedException e) {
            throw new DataConectionExeption("Error SELECT ALL from table PEOPLE");
        } finally {
           ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }


        return people;

    }

    @Override
    public void deleteById(Long id) throws DataConectionExeption {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPoolImpl.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM people WHERE id = ?");
            preparedStatement.setInt(1, Math.toIntExact(id));
            preparedStatement.executeUpdate();
            logger.info("Record deleted");
        } catch (SQLException | InterruptedException e) {
            throw new DataConectionExeption("Error deleting person");
        }finally {
            ConnectionPoolImpl.getInstance().releaseConnection(connection);
        }


    }

    @Override
    public void deletePersonByObject(Person p) throws DataConectionExeption {
        deleteById(p.getId());

    }
    private Person fillPersonByResultSet(ResultSet resultSet) throws DataConectionExeption {
        Person person = new Person();
        try {
            person.setId(resultSet.getLong(1));
            person.setName(resultSet.getString(2));
            person.setLastName(resultSet.getString(3));
            person.setDataBirth(resultSet.getDate(4));
            person.setCity(personUtiles.getCityById(resultSet.getLong(5)));
            person.setWorkerId(resultSet.getLong(6));
            person.setStartDate(resultSet.getDate(7));
            person.setRole(personUtiles.getRoleById(resultSet.getLong(8)));
            person.setPilotLicense(personUtiles.getLicenseById(resultSet.getLong(9)));
        } catch (SQLException e) {
            throw new DataConectionExeption("Error filling person from resutlset");
        }

        return person;
    }
}

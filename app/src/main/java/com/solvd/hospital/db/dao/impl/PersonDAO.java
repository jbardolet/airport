package com.solvd.hospital.db.dao.impl;

import com.solvd.hospital.db.dao.IPersonDAO;
import com.solvd.hospital.db.dao.model.Person;
import com.solvd.hospital.db.utils.mysql.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PersonDAO implements IPersonDAO {
    private static final Logger logger = LogManager.getLogger("CityDAO");
    @Override
    public void insert(Person person) {

    }

    @Override
    public Person getById(Integer id) {
        return null;
    }

    @Override
    public List<Person> getAll() {
        return null;
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
}

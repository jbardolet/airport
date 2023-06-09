package com.solvd.airport.db.dao.impl;

import com.solvd.airport.db.dao.IDAO;
import com.solvd.airport.db.dao.model.Airline;
import com.solvd.airport.db.utils.mysql.ConnectionPoolImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirlineDAO implements IDAO<Airline> {


    @Override
    public List<Airline> getAll() throws SQLException, InterruptedException {
        List<Airline> airlines = new ArrayList<>();
        Connection connection = ConnectionPoolImpl.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM airlines");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Airline airline= new Airline();
            airline.setId(resultSet.getLong(1));
            airline.setName(resultSet.getString(2));
            airlines.add(airline);
        }
        ConnectionPoolImpl.getInstance().releaseConnection(connection);

        return airlines;
    }

    @Override
    public void deletePersonById(Long id) throws SQLException, InterruptedException {

    }

    @Override
    public void insert(Airline airline) throws SQLException, InterruptedException {

    }

    @Override
    public Airline getById(Integer id) {
        return null;
    }
}

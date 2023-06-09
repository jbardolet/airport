package com.solvd.hospital.db.dao.impl;

import com.solvd.hospital.db.dao.IDAO;
import com.solvd.hospital.db.dao.model.Airline;
import com.solvd.hospital.db.dao.model.Airplane;
import com.solvd.hospital.db.dao.model.City;
import com.solvd.hospital.db.utils.mysql.ConnectionPoolImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirlineDAO implements IDAO<Airline> {
    @Override
    public void insert(Airline airline) throws SQLException, InterruptedException {

    }

    @Override
    public Airline getById(Integer id) {
        return null;
    }

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
}

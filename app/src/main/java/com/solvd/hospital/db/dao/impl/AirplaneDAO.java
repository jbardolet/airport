package com.solvd.hospital.db.dao.impl;

import com.solvd.hospital.db.dao.IDAO;
import com.solvd.hospital.db.dao.model.Airplane;
import com.solvd.hospital.db.dao.model.City;
import com.solvd.hospital.db.utils.mysql.ConnectionPoolImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirplaneDAO implements IDAO<Airplane> {

    @Override
    public void insert(Airplane airplane) throws SQLException, InterruptedException {

    }

    @Override
    public Airplane getById(Integer id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPoolImpl.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM airplanes WHERE id=?");
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Airplane airplane = new Airplane();
        airplane.setId(resultSet.getLong(1));
        airplane.setName(resultSet.getString(2));
        return airplane;

    }

    public List<Airplane> getAll() throws SQLException, InterruptedException {
        List<Airplane> airplanes = new ArrayList<>();
        Connection connection = ConnectionPoolImpl.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM airplanes");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Airplane airplane= new Airplane();
            airplane.setId(resultSet.getLong(1));
            airplane.setName(resultSet.getString(2));
            airplanes.add(airplane);
        }
        ConnectionPoolImpl.getInstance().releaseConnection(connection);

        return airplanes;
    }
}

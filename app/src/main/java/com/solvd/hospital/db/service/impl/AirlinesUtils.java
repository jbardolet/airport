package com.solvd.hospital.db.service.impl;

import com.solvd.hospital.db.dao.impl.AirlineDAO;
import com.solvd.hospital.db.dao.impl.AirplaneDAO;
import com.solvd.hospital.db.dao.model.Airline;
import com.solvd.hospital.db.dao.model.Airplane;
import com.solvd.hospital.db.service.IAirlines;
import com.solvd.hospital.db.utils.mysql.ConnectionPoolImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirlinesUtils implements IAirlines {

    AirlineDAO airlineDAO = new AirlineDAO();


    public List<Airplane> selelctByFirstAirline() throws SQLException, InterruptedException {
        List<Airplane> airplanes = new ArrayList<>();
        Connection connection = ConnectionPoolImpl.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM airplanes WHERE id_Airline = ?");
        preparedStatement.setLong(1, airlineDAO.getAll().get(0).getId());
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

package com.solvd.airport.db.service;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.model.Airplane;

import java.sql.SQLException;
import java.util.List;

public interface IAirlines {

    List<Airplane> selelctByFirstAirline() throws SQLException, InterruptedException, DataConectionExeption;
}

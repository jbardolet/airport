package com.solvd.airport.db.service;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.model.Airline;
import com.solvd.airport.db.dao.model.Gate;

public interface IAirplane {
    Airline getAirlineById(Long id) throws DataConectionExeption;
    Gate getGateById(Long id) throws DataConectionExeption;
}

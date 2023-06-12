package com.solvd.airport.db.service;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.model.Airplane;

public interface ISeat {
    Airplane getAirplaneById(Long id) throws DataConectionExeption;
}

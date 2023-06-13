package com.solvd.airport.db.service;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.model.Airplane;
import com.solvd.airport.db.dao.model.City;

public interface ITrip {
    City getCityById(Long id) throws DataConectionExeption;
    Airplane getAirplaneById(Long id) throws DataConectionExeption;
}

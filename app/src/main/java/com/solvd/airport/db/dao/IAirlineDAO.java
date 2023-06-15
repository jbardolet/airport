package com.solvd.airport.db.dao;

import com.solvd.airport.db.dao.model.Airline;

public interface IAirlineDAO extends IDAO<Airline> {
    Airline getAirlineByAirplaneId(Long id) throws DataConectionExeption;
}

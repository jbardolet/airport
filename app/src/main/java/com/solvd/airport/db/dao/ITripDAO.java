package com.solvd.airport.db.dao;

import com.solvd.airport.db.dao.model.Trip;

public interface ITripDAO extends IDAO<Trip> {

    Trip getTripByCrewServiceId(Long id) throws DataConectionExeption;
}

package com.solvd.airport.db.service;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.model.Person;
import com.solvd.airport.db.dao.model.Trip;

public interface ICrewService {
    Person getPersonById(Long id) throws DataConectionExeption;
    Trip getTripById(Long id) throws DataConectionExeption;
}

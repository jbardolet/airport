package com.solvd.airport.db.service;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.model.*;

public interface ITicket {
    Trip selectTripById(Long id) throws DataConectionExeption;
    Luggage selectLuggageById(Long id) throws DataConectionExeption;
    Food selectFoodById(Long id) throws DataConectionExeption;
    Seat selectSeatById(Long id) throws DataConectionExeption;
    Person selectPersonById(Long id) throws DataConectionExeption;
}

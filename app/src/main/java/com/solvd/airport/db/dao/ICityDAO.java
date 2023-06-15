package com.solvd.airport.db.dao;

import com.solvd.airport.db.dao.model.City;
import com.solvd.airport.db.dao.model.Role;

import java.sql.SQLException;
import java.util.List;

public interface ICityDAO extends IDAO<City> {

    List<City> selectByState(String state) throws SQLException, InterruptedException, DataConectionExeption;
    City getCityByPersonId(Long id) throws DataConectionExeption;
    City getCityFromByTripId(Long id) throws DataConectionExeption;
    City getCityToByTripId(Long id) throws DataConectionExeption;
}

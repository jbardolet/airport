package com.solvd.airport.db.dao;

import com.solvd.airport.db.dao.model.City;

public interface ICityDAO extends IDAO<City> {

    City selectByState(String state);
}

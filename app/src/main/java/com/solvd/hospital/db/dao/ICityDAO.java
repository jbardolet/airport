package com.solvd.hospital.db.dao;

import com.solvd.hospital.db.dao.model.City;

public interface ICityDAO extends IDAO<City> {

    City selectByState(String state);
}

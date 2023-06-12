package com.solvd.airport.db.service;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.model.City;
import com.solvd.airport.db.dao.model.PilotLicense;
import com.solvd.airport.db.dao.model.Role;

public interface IPerson {

    Role getRoleById(Long id) throws DataConectionExeption;
    PilotLicense getLicenseById(Long id) throws DataConectionExeption;

    City getCityById(Long id)throws DataConectionExeption;
}

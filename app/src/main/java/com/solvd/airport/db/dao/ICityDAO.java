package com.solvd.airport.db.dao;

import com.solvd.airport.db.dao.model.City;

import java.sql.SQLException;
import java.util.List;

public interface ICityDAO extends IDAO<City> {

    List<City> selectByState(String state) throws SQLException, InterruptedException, DataConectionExeption;
}

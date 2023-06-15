package com.solvd.airport.db.dao;

import com.solvd.airport.db.dao.model.Gate;

public interface IGateDAO extends IDAO<Gate> {
    Gate getGatByAirplaneId(Long id)throws DataConectionExeption;
}

package com.solvd.airport.db.dao;

import com.solvd.airport.db.dao.model.Seat;

import java.util.List;

public interface ISeatDAO extends IDAO<Seat> {
    List<Seat> selectByAirplaneId(Long id)throws DataConectionExeption;
}

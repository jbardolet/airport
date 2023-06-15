package com.solvd.airport.db.dao;

import com.solvd.airport.db.dao.model.Airplane;

public interface IAirplaneDAO extends IDAO<Airplane> {

    Airplane getAriplaneBySeatId(Long id)  throws DataConectionExeption;
    Airplane getAriplaneByTripId(Long id)  throws DataConectionExeption;
}

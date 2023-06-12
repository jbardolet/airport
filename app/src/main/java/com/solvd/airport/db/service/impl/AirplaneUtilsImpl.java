package com.solvd.airport.db.service.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.impl.AirlineDAO;
import com.solvd.airport.db.dao.impl.GatesDAO;
import com.solvd.airport.db.dao.model.Airline;
import com.solvd.airport.db.dao.model.Airplane;
import com.solvd.airport.db.dao.model.Gate;

public class AirplaneUtilsImpl {

    private AirlineDAO airlineDAO = new AirlineDAO();
    private GatesDAO gatesDAO = new GatesDAO();

    public Airline getAirlineById(Long id) throws DataConectionExeption {
        return airlineDAO.getById(id);
    }

    public Gate getGateById(Long id) throws DataConectionExeption{
        return gatesDAO.getById(id);
    }

}

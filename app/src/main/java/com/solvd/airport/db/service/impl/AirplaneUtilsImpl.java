package com.solvd.airport.db.service.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.impl.AirlineDAO;
import com.solvd.airport.db.dao.impl.AirplaneDAO;
import com.solvd.airport.db.dao.impl.GatesDAO;
import com.solvd.airport.db.dao.model.Airline;
import com.solvd.airport.db.dao.model.Airplane;
import com.solvd.airport.db.dao.model.Gate;
import com.solvd.airport.db.service.IAirplane;

import java.util.ArrayList;
import java.util.List;

public class AirplaneUtilsImpl implements IAirplane {

    private AirlineDAO airlineDAO = new AirlineDAO();
    private GatesDAO gatesDAO = new GatesDAO();
    private AirplaneDAO airplaneDAO = new AirplaneDAO();

    @Override
    public Airplane getById(Long id) throws DataConectionExeption {
        Airplane airplane = airplaneDAO.getById(id);
        airplane.setGate(gatesDAO.getGatByAirplaneId(id));
        airplane.setAirline(airlineDAO.getAirlineByAirplaneId(id));
        return airplane;
    }

    @Override
    public List<Airplane> getAll() throws DataConectionExeption {
        List<Airplane> airplanes = airplaneDAO.getAll();
        for(Airplane a: airplanes){
            a.setGate(gatesDAO.getGatByAirplaneId(a.getId()));
            a.setAirline(airlineDAO.getAirlineByAirplaneId(a.getId()));
        }
        return airplanes;
    }

    @Override
    public void insert(Airplane airplane) throws DataConectionExeption {
        airplaneDAO.insert(airplane);
    }

    @Override
    public void deleteById(Long id) throws DataConectionExeption {
        airplaneDAO.deleteById(id);
    }

}

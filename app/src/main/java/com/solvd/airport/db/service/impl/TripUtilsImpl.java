package com.solvd.airport.db.service.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.impl.AirplaneDAO;
import com.solvd.airport.db.dao.impl.CityDAO;
import com.solvd.airport.db.dao.model.Airplane;
import com.solvd.airport.db.dao.model.City;
import com.solvd.airport.db.service.ITrip;

public class TripUtilsImpl implements ITrip {
    private CityDAO cityDAO = new CityDAO();
    private AirplaneDAO airplaneDAO = new AirplaneDAO();

    @Override
    public City getCityById(Long id) throws DataConectionExeption {
        return cityDAO.getById(id);
    }

    @Override
    public Airplane getAirplaneById(Long id) throws DataConectionExeption {
        return airplaneDAO.getById(id);
    }
}

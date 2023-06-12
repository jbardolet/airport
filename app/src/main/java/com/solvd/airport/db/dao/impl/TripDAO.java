package com.solvd.airport.db.dao.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.IDAO;
import com.solvd.airport.db.dao.model.Trip;

import java.util.List;

public class TripDAO implements IDAO<Trip> {

    @Override
    public void insert(Trip trip) throws DataConectionExeption {

    }

    @Override
    public Trip getById(Long id) throws DataConectionExeption {
        return null;
    }

    @Override
    public List<Trip> getAll() throws DataConectionExeption {
        return null;
    }

    @Override
    public void deleteById(Long id) throws DataConectionExeption {

    }
}

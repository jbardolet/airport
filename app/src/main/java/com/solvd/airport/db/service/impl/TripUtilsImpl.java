package com.solvd.airport.db.service.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.impl.AirplaneDAO;
import com.solvd.airport.db.dao.impl.CityDAO;
import com.solvd.airport.db.dao.impl.TripDAO;
import com.solvd.airport.db.dao.model.Airplane;
import com.solvd.airport.db.dao.model.City;
import com.solvd.airport.db.dao.model.Trip;
import com.solvd.airport.db.service.ITrip;

import java.util.List;

public class TripUtilsImpl implements ITrip {
    private CityDAO cityDAO = new CityDAO();
    private AirplaneDAO airplaneDAO = new AirplaneDAO();
    private TripDAO tripDAO = new TripDAO();


    @Override
    public Trip getById(Long id) throws DataConectionExeption {
        Trip trip = tripDAO.getById(id);
        trip.setCityFrom(cityDAO.getCityFromByTripId(id));
        trip.setCityTo(cityDAO.getCityToByTripId(id));
        trip.setAirplane(airplaneDAO.getAriplaneByTripId(id));
        return trip;
    }

    @Override
    public List<Trip> getAll() throws DataConectionExeption {
        List<Trip> trips = tripDAO.getAll();
        for(Trip t : trips){
            t.setAirplane(airplaneDAO.getAriplaneByTripId(t.getId()));
            t.setCityFrom(cityDAO.getCityFromByTripId(t.getId()));
            t.setCityTo(cityDAO.getCityToByTripId(t.getId()));
        }
        return trips;
    }

    @Override
    public void insert(Trip trip) throws DataConectionExeption {
        tripDAO.insert(trip);
    }

    @Override
    public void deleteById(Long id) throws DataConectionExeption {
        tripDAO.deleteById(id);
    }
}

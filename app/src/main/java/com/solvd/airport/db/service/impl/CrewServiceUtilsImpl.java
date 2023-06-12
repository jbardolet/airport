package com.solvd.airport.db.service.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.impl.PersonDAO;
import com.solvd.airport.db.dao.impl.TripDAO;
import com.solvd.airport.db.dao.model.Person;
import com.solvd.airport.db.dao.model.Trip;
import com.solvd.airport.db.service.ICrewService;

public class CrewServiceUtilsImpl implements ICrewService {

    private PersonDAO personDAO = new PersonDAO();
    private TripDAO tripDAO = new TripDAO();

    @Override
    public Person getPersonById(Long id) throws DataConectionExeption {
        return personDAO.getById(id);
    }
    @Override
    public Trip getTripById(Long id) throws DataConectionExeption {
        return tripDAO.getById(id);
    }
}

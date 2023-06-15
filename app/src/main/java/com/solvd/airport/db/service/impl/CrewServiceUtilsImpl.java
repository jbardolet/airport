package com.solvd.airport.db.service.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.impl.CrewServiceDAO;
import com.solvd.airport.db.dao.impl.PersonDAO;
import com.solvd.airport.db.dao.impl.TripDAO;
import com.solvd.airport.db.dao.model.CrewService;
import com.solvd.airport.db.dao.model.Person;
import com.solvd.airport.db.dao.model.Trip;
import com.solvd.airport.db.service.ICrewService;

import java.util.List;

public class CrewServiceUtilsImpl implements ICrewService {

    private PersonDAO personDAO = new PersonDAO();
    private TripDAO tripDAO = new TripDAO();
    private CrewServiceDAO crewServiceDAO = new CrewServiceDAO();


    @Override
    public CrewService getById(Long id) throws DataConectionExeption {
        CrewService crewService = crewServiceDAO.getById(id);
        crewService.setPerson(personDAO.getPersonByCrewServiceId(id));
        crewService.setTrip(tripDAO.getTripByCrewServiceId(id));
        return crewService;
    }

    @Override
    public List<CrewService> getAll() throws DataConectionExeption {
        List<CrewService> crewServices = crewServiceDAO.getAll();
        for(CrewService c:crewServices){
            c.setTrip(tripDAO.getTripByCrewServiceId(c.getId()));
            c.setPerson(personDAO.getPersonByCrewServiceId(c.getId()));
        }
        return crewServices;
    }

    @Override
    public void insert(CrewService crewService) throws DataConectionExeption {
        crewServiceDAO.insert(crewService);
    }

    @Override
    public void deleteById(Long id) throws DataConectionExeption {
        crewServiceDAO.deleteById(id);
    }
}

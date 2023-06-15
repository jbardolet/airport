package com.solvd.airport.db.service.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.impl.CityDAO;
import com.solvd.airport.db.dao.impl.PersonDAO;
import com.solvd.airport.db.dao.impl.PilotLicenseDAO;
import com.solvd.airport.db.dao.impl.RoleDAO;
import com.solvd.airport.db.dao.model.City;
import com.solvd.airport.db.dao.model.Person;
import com.solvd.airport.db.dao.model.PilotLicense;
import com.solvd.airport.db.dao.model.Role;
import com.solvd.airport.db.service.IPerson;

import java.util.List;

public class PersonUtilsImpl implements IPerson {

    private RoleDAO roleDAO = new RoleDAO();
    private PilotLicenseDAO pilotLicenseDAO = new PilotLicenseDAO();
    private CityDAO cityDAO = new CityDAO();
    private PersonDAO personDAO = new PersonDAO();


    @Override
    public Person getById(Long id) throws DataConectionExeption {
        Person person = personDAO.getById(id);
        person.setPilotLicense(pilotLicenseDAO.getLicenseByPersonId(id));
        person.setCity(cityDAO.getCityByPersonId(id));
        person.setRole(roleDAO.getRoleByPersonId(id));
        return person;
    }

    @Override
    public List<Person> getAll() throws DataConectionExeption {
        List<Person> people = personDAO.getAll();
        for(Person p : people){
            p.setRole(roleDAO.getRoleByPersonId(p.getId()));
            p.setCity(cityDAO.getCityByPersonId(p.getId()));
            p.setPilotLicense(pilotLicenseDAO.getLicenseByPersonId(p.getId()));
        }
        return people;
    }

    @Override
    public void insert(Person person) throws DataConectionExeption {
        personDAO.insert(person);
    }

    @Override
    public void deleteById(Long id) throws DataConectionExeption {
        personDAO.deleteById(id);
    }
}

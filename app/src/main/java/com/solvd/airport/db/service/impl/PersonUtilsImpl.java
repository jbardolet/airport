package com.solvd.airport.db.service.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.impl.CityDAO;
import com.solvd.airport.db.dao.impl.PilotLicenseDAO;
import com.solvd.airport.db.dao.impl.RoleDAO;
import com.solvd.airport.db.dao.model.City;
import com.solvd.airport.db.dao.model.PilotLicense;
import com.solvd.airport.db.dao.model.Role;
import com.solvd.airport.db.service.IPerson;

public class PersonUtilsImpl implements IPerson {

    private RoleDAO roleDAO = new RoleDAO();
    private PilotLicenseDAO pilotLicenseDAO = new PilotLicenseDAO();
    private CityDAO cityDAO = new CityDAO();

    @Override
    public Role getRoleById(Long id) throws DataConectionExeption {
        Role role = roleDAO.getById(id);

        return role;
    }
    @Override
    public PilotLicense getLicenseById(Long id) throws DataConectionExeption{
        PilotLicense pilotLicense = pilotLicenseDAO.getById(id);
        return pilotLicense;
    }

    @Override
    public City getCityById(Long id) throws DataConectionExeption {
        return cityDAO.getById(id);
    }

}

package com.solvd.airport.db.dao;

import com.solvd.airport.db.dao.model.PilotLicense;
import com.solvd.airport.db.dao.model.Role;

public interface IPilotLicenseDAO extends IDAO<PilotLicense> {
    PilotLicense getLicenseByPersonId(Long id) throws DataConectionExeption;
}

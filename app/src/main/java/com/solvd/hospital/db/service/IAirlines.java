package com.solvd.hospital.db.service;

import com.solvd.hospital.db.dao.model.Airplane;

import java.sql.SQLException;
import java.util.List;

public interface IAirlines {

    List<Airplane> selelctByFirstAirline()  throws SQLException, InterruptedException;
}

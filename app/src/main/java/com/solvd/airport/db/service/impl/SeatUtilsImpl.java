package com.solvd.airport.db.service.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.impl.AirlineDAO;
import com.solvd.airport.db.dao.impl.AirplaneDAO;
import com.solvd.airport.db.dao.impl.SeatDAO;
import com.solvd.airport.db.dao.model.Airline;
import com.solvd.airport.db.dao.model.Airplane;
import com.solvd.airport.db.dao.model.Seat;
import com.solvd.airport.db.service.ISeat;

import java.util.List;

public class SeatUtilsImpl implements ISeat {
    private AirplaneDAO airplaneDAO = new AirplaneDAO();
    private SeatDAO seatDAO = new SeatDAO();

    @Override
    public Airplane getAirplaneById(Long id) throws DataConectionExeption {
        return airplaneDAO.getById(id);
    }


}

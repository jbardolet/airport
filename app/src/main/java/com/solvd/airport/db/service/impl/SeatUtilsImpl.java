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
    public Seat getById(Long id) throws DataConectionExeption {
        Seat seat = seatDAO.getById(id);
        seat.setAirplane(airplaneDAO.getAriplaneBySeatId(id));
        return seat;
    }

    @Override
    public List<Seat> getAll() throws DataConectionExeption {
        List<Seat> seats = seatDAO.getAll();
        for(Seat s : seats){
            s.setAirplane(airplaneDAO.getAriplaneBySeatId(s.getId()));
        }
        return seats;
    }

    @Override
    public void insert(Seat seat) throws DataConectionExeption {
        seatDAO.insert(seat);
    }

    @Override
    public void deleteById(Long id) throws DataConectionExeption {
        seatDAO.deleteById(id);
    }
}

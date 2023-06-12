package com.solvd.airport.db.service.impl;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.impl.*;
import com.solvd.airport.db.dao.model.*;
import com.solvd.airport.db.service.ITicket;

public class TicketsUtilsImpl implements ITicket {

    private TripDAO tripDAO = new TripDAO();
    private LuggageDAO luggageDAO = new LuggageDAO();
    private FoodDAO foodDAO = new FoodDAO();
    private SeatDAO seatDAO = new SeatDAO();
    private PersonDAO personDAO = new PersonDAO();

    @Override
    public Trip selectTripById(Long id) throws DataConectionExeption {
        return tripDAO.getById(id);
    }
    @Override
    public Luggage selectLuggageById(Long id) throws DataConectionExeption {
        return luggageDAO.getById(id);
    }
    @Override
    public Food selectFoodById(Long id) throws DataConectionExeption {
        return foodDAO.getById(id);
    }
    @Override
    public Seat selectSeatById(Long id) throws DataConectionExeption {
        return seatDAO.getById(id);
    }
    @Override
    public Person selectPersonById(Long id) throws DataConectionExeption{
        return personDAO.getById(id);
    }


}

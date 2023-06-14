package com.solvd.airport.json.classes;

import com.fasterxml.jackson.annotation.*;
import com.solvd.airport.db.dao.model.*;

import javax.xml.bind.annotation.*;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {
    private Long id;

    private Luggage luggage;

    private Food food;

    private Seat seat;

    private Person person;


    public Ticket(Long id, Luggage luggage, Food food, Seat seat, Person person) {
        this.id = id;
        this.luggage = luggage;
        this.food = food;
        this.seat = seat;
        this.person = person;
    }

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Luggage getLuggage() {
        return luggage;
    }

    public void setLuggage(Luggage luggage) {
        this.luggage = luggage;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}

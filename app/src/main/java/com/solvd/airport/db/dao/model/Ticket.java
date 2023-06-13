package com.solvd.airport.db.dao.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ticket {

    private Long id;
    @XmlElement
    private Luggage luggage;
    @XmlElement
    private Food food;
    @XmlElement
    private Seat seat;
    @XmlElement
    private Person person;
    @XmlTransient
    private Trip trip;

    public Ticket(Long id, Luggage luggage, Food food, Seat seat, Person person, Trip trip) {
        this.id = id;
        this.luggage = luggage;
        this.food = food;
        this.seat = seat;
        this.person = person;
        this.trip = trip;
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

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}

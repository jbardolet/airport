package com.solvd.airport.db.dao.model;

import java.util.List;

public class Airplane {
    private Long id;
    private String name;

    private List<Seat> seats;

    private Gate gate;

    public Airplane() {
    }

    public Airplane(Long id, String name, List<Seat> seats, Gate gate) {
        this.id = id;
        this.name = name;
        this.seats = seats;
        this.gate = gate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seats=" + seats +
                ", gate=" + gate +
                '}';
    }
}

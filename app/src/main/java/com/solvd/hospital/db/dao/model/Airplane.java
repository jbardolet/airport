package com.solvd.hospital.db.dao.model;

import java.util.List;

public class Airplane {
    private Integer id;
    private String name;

    private List<Seat> seats;

    public Airplane() {
    }

    public Airplane(Integer id, String name, List<Seat> seats) {
        this.id = id;
        this.name = name;
        this.seats = seats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}

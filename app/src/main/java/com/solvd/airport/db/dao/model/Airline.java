package com.solvd.airport.db.dao.model;

import java.util.List;

public class Airline {
    private Long id;
    private String name;
    private List<Airplane> airplaneList;

    public Airline() {
    }

    public Airline(Long id, String name, List<Airplane> airplaneList) {
        this.id = id;
        this.name = name;
        this.airplaneList = airplaneList;
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

    public List<Airplane> getAirplaneList() {
        return airplaneList;
    }

    public void setAirplaneList(List<Airplane> airplaneList) {
        this.airplaneList = airplaneList;
    }
}

package com.solvd.airport.db.dao.model;

public class Gate {
    private Long id;
    private Integer number;

    public Gate() {
    }

    public Gate(Long id, Integer number) {
        this.id = id;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}

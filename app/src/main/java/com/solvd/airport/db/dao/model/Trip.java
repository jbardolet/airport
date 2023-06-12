package com.solvd.airport.db.dao.model;

import java.util.Date;

public class Trip {
    private Long id;
    private Airplane airplane;
    private City cityFrom;
    private City cityTo;
    private Date departureDate;

    public Trip() {
    }

    public Trip(Long id, Airplane airplane, City cityFrom, City cityTo, Date departureDate) {
        this.id = id;
        this.airplane = airplane;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.departureDate = departureDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    public City getCityTo() {
        return cityTo;
    }

    public void setCityTo(City cityTo) {
        this.cityTo = cityTo;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
}

package com.solvd.airport.db.dao.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

public class CrewService {
    private Long id;
    private Person person;
    private Trip trip;

    public CrewService(Long id, Person person, Trip trip) {
        this.id = id;
        this.person = person;
        this.trip = trip;
    }

    public CrewService() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

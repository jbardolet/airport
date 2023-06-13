package com.solvd.airport.db.dao.model;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement(name = "airline")
@XmlAccessorType(XmlAccessType.FIELD)
public class Airline {
    private Long id;
    private String name;


    @XmlTransient
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

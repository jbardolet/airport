package com.solvd.airport.json.classes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.airport.db.dao.model.City;
import com.solvd.airport.db.dao.model.PilotLicense;
import com.solvd.airport.db.dao.model.Role;
import com.solvd.airport.parserXML.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value="person")
public class Person {
    private Long id;
    private String name;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataBirth;
    @JsonIgnore
    private Long workerId;
    @JsonIgnore
    private Date startDate;

    private City city;
    @JsonIgnore
    private PilotLicense pilotLicense;
    @JsonIgnore
    private Role role;

    public Person() {
    }

    public Person(Long id, String name, String lastName, Date dataBirth, Long workerId, Date startDate, City city, PilotLicense pilotLicense, Role role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dataBirth = dataBirth;
        this.workerId = workerId;
        this.startDate = startDate;
        this.city = city;
        this.pilotLicense = pilotLicense;
        this.role = role;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDataBirth() {
        return dataBirth;
    }

    public void setDataBirth(Date dataBirth) {
        this.dataBirth = dataBirth;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public PilotLicense getPilotLicense() {
        return pilotLicense;
    }

    public void setPilotLicense(PilotLicense pilotLicense) {
        this.pilotLicense = pilotLicense;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dataBirth=" + dataBirth +
                ", workerId=" + workerId +
                ", startDate=" + startDate +
                ", city=" + city +
                ", pilotLicense=" + pilotLicense +
                ", role=" + role +
                '}';
    }
}

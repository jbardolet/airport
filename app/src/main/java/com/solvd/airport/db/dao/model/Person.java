package com.solvd.airport.db.dao.model;

import java.util.Date;

public class Person {
    private Long id;
    private String name;
    private String lastName;
    private Date dataBirth;
    private Long workerId;
    private Date startDate;
    private City city;
    private PilotLicense pilotLicense;
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
}

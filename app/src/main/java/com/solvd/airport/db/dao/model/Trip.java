package com.solvd.airport.db.dao.model;


import com.solvd.airport.parserXML.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;
@XmlRootElement(name = "trip")
@XmlAccessorType(XmlAccessType.FIELD)
public class Trip {
    private Long id;
    @XmlElement(name = "airplane")
    private Airplane airplane;
    @XmlElement(name = "cityFrom")
    private City cityFrom;
    @XmlElement(name = "cityTo")
    private City cityTo;

    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date departureDate;


    //@XmlElementWrapper(name="tickets")
    @XmlElement(name = "tickets")
   // private List<Ticket> tickets;
    private Tickets tickets;

    public Trip() {
    }

    public Trip(Long id, Airplane airplane, City cityFrom, City cityTo, Date departureDate, Tickets tickets) {
        this.id = id;
        this.airplane = airplane;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.departureDate = departureDate;
        this.tickets = tickets;
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

    public Tickets getTickets() {
        return tickets;
    }

    public void setTickets(Tickets tickets) {
        this.tickets = tickets;
    }
}

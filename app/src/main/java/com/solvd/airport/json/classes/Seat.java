package com.solvd.airport.json.classes;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.airport.db.dao.model.Airplane;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@JsonRootName(value="seat")
public class Seat {
    private Long id;
    private Integer seatNumber;
    private String seatLetter;
    private Float price;



    public Seat() {
    }

    public Seat(Long id, Integer seatNumber, String seatLetter, Float price) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.seatLetter = seatLetter;
        this.price = price;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatLetter() {
        return seatLetter;
    }

    public void setSeatLetter(String seatLetter) {
        this.seatLetter = seatLetter;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

}

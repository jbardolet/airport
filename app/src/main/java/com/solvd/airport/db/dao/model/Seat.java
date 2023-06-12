package com.solvd.airport.db.dao.model;

public class Seat {
    private Long id;
    private Integer seatNumber;
    private String seatLetter;
    private Float price;
    private Airplane airplane;


    public Seat() {
    }

    public Seat(Long id, Integer seatNumber, String seatLetter, Float price, Airplane airplane) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.seatLetter = seatLetter;
        this.price = price;
        this.airplane = airplane;
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

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }
}

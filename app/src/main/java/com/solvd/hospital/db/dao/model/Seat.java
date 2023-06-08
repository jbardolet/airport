package com.solvd.hospital.db.dao.model;

public class Seat {
    private Integer id;
    private Integer seatNumber;
    private Integer seatLetter;
    private Float price;

    public Seat() {
    }

    public Seat(Integer id, Integer seatNumber, Integer seatLetter, Float price) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.seatLetter = seatLetter;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Integer getSeatLetter() {
        return seatLetter;
    }

    public void setSeatLetter(Integer seatLetter) {
        this.seatLetter = seatLetter;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

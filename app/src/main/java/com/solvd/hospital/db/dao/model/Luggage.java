package com.solvd.hospital.db.dao.model;

public class Luggage {
    private Long id;
    private Float price;
    private String description;

    public Luggage() {
    }

    public Luggage(Long id, Float price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

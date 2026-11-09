package com.example.rental;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Car {
    private String plateNumber;
    private String brand;
    private int price;
    @JsonIgnore
    private boolean rented = false;
    @JsonIgnore
    private Dates dates;

    public Car() {}
    public Car(String plateNumber, String brand, int price) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.price = price;
    }

    public String getPlateNumber() { return plateNumber; }
    public String getBrand() { return brand; }
    public int getPrice() { return price; }
    public boolean isRented() { return rented; }
    public void setRented(boolean rented) { this.rented = rented; }
    public Dates getDates() { return dates; }
    public void setDates(Dates dates) { this.dates = dates; }
}
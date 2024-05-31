package com.project.model;

public class Airline extends FlightEntity implements DatabaseEntity {
    private int id;
    private String airlineCode;

    public Airline(String name, String airlineCode) {
        super(name);
        this.airlineCode = airlineCode;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Airline{" + "name='" + super.getName() + "}";
    }
}

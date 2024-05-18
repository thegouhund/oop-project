package com.project.model;

public class Airline extends Entity implements DatabaseEntity {
    private String airlineCode;
    private int id;

    public Airline(String name) {
        super(name);
        this.airlineCode = "";
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

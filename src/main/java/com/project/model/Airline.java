package com.project.model;

public class Airline extends Entity {
    private String airlineCode;
    private double priceMultiplier = 1;

    public Airline(String name, double priceMultiplier) {
        super(name);
        this.airlineCode = "";
        this.priceMultiplier = priceMultiplier;
    }

    public Airline(String name) {
        super(name);
        this.airlineCode = "";
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "name='" + super.getName() + '\'' +
                ", priceMultiplier=" + priceMultiplier +
                '}';
    }
}

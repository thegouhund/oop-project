package com.project.model;

public class Airline extends Entity{

    private double priceMultiplier = 1;

    public Airline(String name, double priceMultiplier) {
        super(name);
        this.priceMultiplier = priceMultiplier;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "name='" + super.getName() + '\'' +
                ", priceMultiplier=" + priceMultiplier +
                '}';
    }
}

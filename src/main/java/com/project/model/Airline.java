package com.project.model;

import java.util.ArrayList;

public class Airline {
    private String name;
    private double priceMultiplier = 1;

    public Airline(String name, double priceMultiplier) {
        this.name = name;
        this.priceMultiplier = priceMultiplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }
}

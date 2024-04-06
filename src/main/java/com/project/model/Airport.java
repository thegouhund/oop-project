package com.project.model;

public class Airport {
    private String name;
    private String iata;
    private String city;

    public Airport(String name, String iata, String city) {
        this.name = name;
        this.iata = iata;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String toString() {
        return this.city + " (" + iata + ")";
    }
}

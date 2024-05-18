package com.project.model;

public class Airport implements DatabaseEntity{
    private int id;
    private String iata;
    private String city;

    public Airport(String iata, String city) {
        this.iata = iata;
        this.city = city;
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return this.city + " (" + iata + ")";
    }
}

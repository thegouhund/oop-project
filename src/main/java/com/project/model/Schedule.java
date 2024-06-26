package com.project.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Schedule implements DatabaseEntity {
    private int id;
    private Airline airline;
    private double price;
    private Airport airportFrom;
    private Airport airportDestination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private ArrayList<Passenger> passengers;

    public Schedule(Airline airline, Airport airportFrom, Airport airportDestination, LocalDateTime departureTime, LocalDateTime arrivalTime, double price) {
        this.airline = airline;
        this.airportFrom = airportFrom;
        this.airportDestination = airportDestination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public Schedule() {
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Airport getAirportFrom() {
        return airportFrom;
    }

    public void setAirportFrom(Airport airportFrom) {
        this.airportFrom = airportFrom;
    }

    public Airport getAirportDestination() {
        return airportDestination;
    }

    public void setAirportDestination(Airport airportDestination) {
        this.airportDestination = airportDestination;
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
        return "Schedule{" + "airline=" + airline + ", price=" + price + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + '}';
    }
}

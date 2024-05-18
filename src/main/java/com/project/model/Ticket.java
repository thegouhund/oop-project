package com.project.model;

import java.util.ArrayList;

public class Ticket implements DatabaseEntity {
    private int id;
    private ArrayList<Passenger> passengers = new ArrayList<>();
    private Schedule schedule;

    public Ticket(ArrayList<Passenger> passengers, Schedule schedule) {
        this.passengers = passengers;
        this.schedule = schedule;
    }

    public Ticket(Schedule schedule) {
        this.schedule = schedule;
        this.schedule.setPrice(this.schedule.getPrice() * this.passengers.size());
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}

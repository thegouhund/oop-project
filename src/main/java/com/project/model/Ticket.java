package com.project.model;

import java.util.ArrayList;
import java.util.Date;

public class Ticket {
    private ArrayList<Passenger> passengers;
    private Schedule schedule;

    public Ticket(ArrayList<Passenger> passengers, Schedule schedule) {
        this.passengers = passengers;
        this.schedule = schedule;
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
}

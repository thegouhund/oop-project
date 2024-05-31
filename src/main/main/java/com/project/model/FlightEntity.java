package com.project.model;

public abstract class FlightEntity {
    private String name;

    public FlightEntity(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

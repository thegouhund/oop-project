package com.project.model;

public abstract class Entity {
    private String name;

    public Entity(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

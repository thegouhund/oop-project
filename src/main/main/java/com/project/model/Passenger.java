package com.project.model;

public class Passenger extends FlightEntity implements DatabaseEntity{
    private int id;
    private int age;

    public Passenger(String name, int age) {
        super(name);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        return "Passenger{" +
                "name='" + super.getName() + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

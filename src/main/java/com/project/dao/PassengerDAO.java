package com.project.dao;

import com.project.model.Passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PassengerDAO extends DAO<Passenger> {

    public PassengerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Passenger getById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM passenger WHERE id = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Passenger(result.getString("name"), result.getInt("age"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Passenger> getAll() {
        Passenger passenger;
        ArrayList<Passenger> passengerList = new ArrayList<Passenger>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM passenger");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                passenger = new Passenger(result.getString("name"), result.getInt("age"));
                passengerList.add(passenger);
            }
            return passengerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Passenger passenger, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE passenger SET name = ?, age = ? WHERE id = ?");
            statement.setString(1, passenger.getName());
            statement.setInt(2, passenger.getAge());
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE from passenger WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Passenger passenger) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO passenger (name, age) VALUES (?, ?)");
            statement.setString(1, passenger.getName());
            statement.setInt(2, passenger.getAge());
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

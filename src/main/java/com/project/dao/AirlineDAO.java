package com.project.dao;

import com.project.model.Airline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AirlineDAO extends DAO<Airline> {

    public AirlineDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Airline getById(int id) {
        Airline airline;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM airline WHERE id = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                airline = new Airline(result.getString("name"));
                airline.setId(result.getInt("id"));

                return airline;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Airline> getAll() {
        Airline airline;
        ArrayList<Airline> airlineList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM airline");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                airline = new Airline(result.getString("name"));
                airline.setId(result.getInt("id"));
                airlineList.add(airline);
            }
            return airlineList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Airline airline, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE airline SET name = ? WHERE id = ?");
            statement.setString(1, airline.getName());
            statement.setInt(2, id);
            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE from airline WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void add(Airline airline) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO airline (name) VALUES (?)");
            statement.setString(1, airline.getName());
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

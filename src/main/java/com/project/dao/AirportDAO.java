package com.project.dao;

import com.project.model.Airport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AirportDAO extends DAO<Airport> {

    public AirportDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Airport getById(int id) {
        Airport airport;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM airport WHERE id = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                airport = new Airport(result.getString("iata"), result.getString("city"));
                airport.setId(result.getInt("id"));

                return airport;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Airport> getAll() {
        Airport airport;
        ArrayList<Airport> airportList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM airport");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                airport = new Airport(result.getString("iata"), result.getString("city"));
                airport.setId(result.getInt("id"));
                airportList.add(airport);
            }
            return airportList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Airport airport, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE airport SET iata = ?, city = ? WHERE id = ?");
            statement.setString(1, airport.getIata());
            statement.setString(2, airport.getCity());
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE from airport WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Airport airport) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO airport (iata, city) VALUES (?, ?)");
            statement.setString(1, airport.getIata());
            statement.setString(2, airport.getCity());
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.project.dao;

import com.project.model.Passenger;
import com.project.model.Schedule;
import com.project.model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketDAO extends DAO<Ticket> {

    PassengerDAO passengerDAO = new PassengerDAO(connection);
    ScheduleDAO scheduleDAO = new ScheduleDAO(connection);

    public TicketDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Ticket getById(int id) {
//        try {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ticket WHERE id = ?");
//            statement.setInt(1, id);
//            ResultSet result = statement.executeQuery();
//            if (result.next()) {
//                return new Ticket(result.getInt("passenger_id"), result.getInt("schedule_id"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    @Override
    public ArrayList<Ticket> getAll() {
//        Ticket ticket;
//        ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
//        try {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ticket");
//            ResultSet result = statement.executeQuery();
//            while (result.next()) {
//                ticket = new Ticket(result.getInt("name"), result.getInt("age"));
//                ticketList.add(ticket);
//            }
//            return ticketList;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    @Override
    public void update(Ticket ticket, int id) {
//        try {
//            PreparedStatement statement = connection.prepareStatement("UPDATE ticket SET name = ?, age = ? WHERE id = ?");
//            statement.setString(1, ticket.get());
//            statement.setInt(2, ticket.getAge());
//            statement.setInt(3, id);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void delete(int id) {
//        try {
//            PreparedStatement statement = connection.prepareStatement("DELETE from ticket WHERE id = ?");
//            statement.setInt(1, id);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void add(Ticket ticket, Schedule schedule) {
        try {
            for (Passenger passenger : ticket.getPassengers()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO ticket (passenger_id, schedule_id) VALUES (?, ?)");
                statement.setInt(1, passenger.getId());
                statement.setInt(2, schedule.getId());
                System.out.println(statement);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

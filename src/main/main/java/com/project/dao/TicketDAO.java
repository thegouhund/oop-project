package com.project.dao;

import com.project.model.Passenger;
import com.project.model.Schedule;
import com.project.model.Ticket;
import com.project.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketDAO extends DAO<Ticket> {

    PassengerDAO passengerDAO = new PassengerDAO(connection);
    ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
    UserDAO userDAO = new UserDAO(connection);

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
        Ticket ticket;
        ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ticket" );
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Passenger passenger = passengerDAO.getById(result.getInt("passenger_id" ));
//                ticket = new Ticket(result.getInt("name"), result.getInt("age"));
//                ticketList.add(ticket);
            }
            return ticketList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        return null;
    }

    public ArrayList<Ticket> getBySchedule() {
        ArrayList<Ticket> ticketList = new ArrayList<>();
        try {
            PreparedStatement statementSchedule = connection.prepareStatement("SELECT DISTINCT schedule_id FROM ticket" );
            ResultSet resultSchedule = statementSchedule.executeQuery();
            while (resultSchedule.next()) {
                int scheduleId = resultSchedule.getInt("schedule_id" );
                PreparedStatement statementPassenger = connection.prepareStatement("SELECT * FROM ticket WHERE schedule_id =?" );
                statementPassenger.setInt(1, scheduleId);
                ResultSet resultPassenger = statementPassenger.executeQuery();

                ArrayList<Passenger> passengerList = new ArrayList<>();
                int userId = 0;
                while (resultPassenger.next()) {
                    userId = resultPassenger.getInt("user_id" );
                    Passenger passenger = passengerDAO.getById(resultPassenger.getInt("passenger_id" ));
                    passengerList.add(passenger);
                }
                Schedule schedule = scheduleDAO.getById(scheduleId);
                User user = userDAO.getById(userId);

                Ticket ticket = new Ticket(user, passengerList, schedule);
                ticketList.add(ticket);
            }
            return ticketList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Ticket> getByUser(User user) {
        Ticket ticket;
        ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
        try {
            PreparedStatement statementSchedule = connection.prepareStatement("SELECT DISTINCT schedule_id FROM ticket where user_id = ?" );
            statementSchedule.setInt(1, user.getId());
            ResultSet resultSchedule = statementSchedule.executeQuery();
            ;

            while (resultSchedule.next()) {
                PreparedStatement statementPassenger = connection.prepareStatement("SELECT * FROM ticket WHERE schedule_id = ?" );
                statementPassenger.setInt(1, resultSchedule.getInt("schedule_id" ));
                ResultSet resultPassenger = statementPassenger.executeQuery();

                ArrayList<Passenger> passengerList = new ArrayList<>();
                while (resultPassenger.next()) {
                    Passenger passenger = passengerDAO.getById(resultPassenger.getInt("passenger_id" ));
                    passengerList.add(passenger);
                }
                Schedule schedule = scheduleDAO.getById(resultSchedule.getInt("schedule_id" ));

                ticket = new Ticket(user, passengerList, schedule);
                ticketList.add(ticket);
            }
            return ticketList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    public int size() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(DISTINCT schedule_id) FROM ticket" );
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(User user, Ticket ticket, Schedule schedule) {
        try {
            for (Passenger passenger : ticket.getPassengers()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO ticket (user_id, passenger_id, schedule_id) VALUES (?, ?, ?)" );
                statement.setInt(1, user.getId());
                statement.setInt(2, passenger.getId());
                statement.setInt(3, schedule.getId());
                System.out.println(statement);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

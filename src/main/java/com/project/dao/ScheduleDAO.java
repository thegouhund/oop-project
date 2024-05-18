package com.project.dao;

import com.project.model.Schedule;
import com.project.utils.TimeUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleDAO extends DAO<Schedule> {

    private final AirlineDAO airlineDAO = new AirlineDAO(connection);
    private final AirportDAO airportDAO = new AirportDAO(connection);

    public ScheduleDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Schedule getById(int id) {
//        try {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM schedule WHERE id = ?");
//            statement.setInt(1, id);
//            ResultSet result = statement.executeQuery();
//            if (result.next()) {
//                return new Schedule(result.getString("name"), result.getInt("age"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
        return null;
    }

    @Override
    public ArrayList<Schedule> getAll() {
        Schedule schedule;
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM schedule");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                schedule = new Schedule();
                schedule.setId(result.getInt("id"));
                schedule.setAirline(airlineDAO.getById(result.getInt("airline_id")));
                schedule.setAirportFrom(airportDAO.getById(result.getInt("airport_arrival_id")));
                schedule.setAirportDestination(airportDAO.getById(result.getInt("airport_departure_id")));
                schedule.setArrivalTime(TimeUtils.strToLocalDateTime(result.getString("arrival_time")));
                schedule.setDepartureTime(TimeUtils.strToLocalDateTime(result.getString("departure_time")));
                schedule.setPrice(result.getDouble("price"));
                scheduleList.add(schedule);
            }
            return scheduleList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Schedule schedule, int id) {
//        try {
//            PreparedStatement statement = connection.prepareStatement("UPDATE schedule SET name = ?, age = ? WHERE id = ?");
//            statement.setString(1, schedule.getName());
//            statement.setInt(2, schedule.getAge());
//            statement.setInt(3, id);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE from schedule WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Schedule schedule) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO schedule (airline_id, airport_arrival_id, airport_departure_id, arrival_time, departure_time, price) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setInt(1, schedule.getAirline().getId());
            statement.setInt(2, schedule.getAirportFrom().getId());
            statement.setInt(3, schedule.getAirportDestination().getId());
            statement.setString(4, TimeUtils.localDateTimeToStr(schedule.getArrivalTime()));
            statement.setString(5, TimeUtils.localDateTimeToStr(schedule.getDepartureTime()));
            statement.setDouble(6, schedule.getPrice());
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int addAndGetGeneratedId(Schedule schedule, Double price) { // price set to 0 for unknown reason (use this param for temp) TODO
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO schedule (airline_id, airport_arrival_id, airport_departure_id, arrival_time, departure_time, price) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, schedule.getAirline().getId());
            statement.setInt(2, schedule.getAirportFrom().getId());
            statement.setInt(3, schedule.getAirportDestination().getId());
            statement.setString(4, TimeUtils.localDateTimeToStr(schedule.getArrivalTime()));
            statement.setString(5, TimeUtils.localDateTimeToStr(schedule.getDepartureTime()));
            statement.setDouble(6, price);
            System.out.println(statement);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve auto-generated ID.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

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

    public int getIdByObject(Schedule schedule) {
//      airline_id, airport_arrival_id, and airport_departure_id combined are unique for each row
        PreparedStatement selectStatement;
        try {
            selectStatement = connection.prepareStatement("SELECT id FROM schedule WHERE airline_id = ? AND airport_arrival_id = ? AND airport_departure_id = ?");
            selectStatement.setInt(1, airlineDAO.getIdByName(schedule.getAirline().getName()));
            selectStatement.setInt(2, airportDAO.getIdByIata(schedule.getAirportFrom().getIata()));
            selectStatement.setInt(3, airportDAO.getIdByIata(schedule.getAirportDestination().getIata()));
            System.out.println(selectStatement);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                if (id != 0) {
                    return id;
                } else {
                    throw new SQLException("No Schedule Found with " + schedule);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
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
            statement.setInt(1, airlineDAO.getIdByName(schedule.getAirline().getName()));
            statement.setInt(2, airportDAO.getIdByIata(schedule.getAirportFrom().getIata()));
            statement.setInt(3, airportDAO.getIdByIata(schedule.getAirportDestination().getIata()));
            statement.setString(4, TimeUtils.localDateTimeToStr(schedule.getArrivalTime()));
            statement.setString(5, TimeUtils.localDateTimeToStr(schedule.getDepartureTime()));
            statement.setDouble(6, schedule.getPrice());
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

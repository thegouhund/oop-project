package com.project.api;

import com.project.controller.AirlineController;
import com.project.model.Airline;
import com.project.model.Airport;
import com.project.model.Schedule;
import com.project.utils.RandomUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.project.utils.TimeUtils.strToLocalDateTime;

public class ScheduleMockApi {
    public static Schedule generate(Airport airportFrom, Airport airportDestination, String strDate, int passengerAmount) {
        // randomize departure time from 00:00 to 23:00
        LocalDateTime departure = strToLocalDateTime(strDate);
        departure = departure.plusHours(RandomUtils.random().nextInt(23));

        // randomize airlines
        ArrayList<Airline> airlines = new ArrayList<>(AirlineController.getAllAirline());
        Airline airline = airlines.get(RandomUtils.random().nextInt(airlines.size()));

        // randomize price
        double price = RandomUtils.random().nextDouble(1000000, 3000000);
        return new Schedule(airline, airportFrom, airportDestination, departure, departure.plusHours(3), price * airline.getPriceMultiplier() * passengerAmount);
    }
}

package com.project.factory;

import com.project.model.Airport;
import com.project.model.Schedule;
import com.project.utils.RandomUtils;

import java.time.LocalDateTime;

import static com.project.utils.TimeUtils.strToLocalDateTime;

public class ScheduleFactory {
    public static Schedule generate(Airport airportFrom, Airport airportDestination, String strDate) {
        LocalDateTime departure = strToLocalDateTime(strDate);
        return new Schedule(AirlineFactory.generate(), airportFrom, airportDestination, departure.plusMinutes(RandomUtils.random().nextInt(15)), departure.plusHours(3), 1000000);
    }
}

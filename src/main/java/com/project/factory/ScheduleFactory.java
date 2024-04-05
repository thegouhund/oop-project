package com.project.factory;

import com.project.model.Schedule;

import java.time.LocalDateTime;

import static com.project.utils.TimeUtils.strToLocalDateTime;

public class ScheduleFactory {
    public static Schedule generate(String strDate) {
        LocalDateTime departure = strToLocalDateTime(strDate);
        return new Schedule(AirlineFactory.generate(), departure.plusHours(5), departure.plusHours(3));

    }
}

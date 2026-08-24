package com.app.bookai.barber.application.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public record CreateWorkingHourOverrideDTO(
        String reason,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime
) {
}

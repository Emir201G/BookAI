package com.app.bookai.barber.application.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record WorkingHourRequestDTO(
        DayOfWeek dayOfWeek,
        LocalTime startTime,
        LocalTime endTime
) {
}

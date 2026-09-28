package com.app.bookai.ai.infrastructure.tool.dto.barber;

import java.time.DayOfWeek;
import java.time.LocalTime;
public record WorkingHourToolRequest(
        DayOfWeek dayOfWeek,
        LocalTime startTime,
        LocalTime endTime
) {
}

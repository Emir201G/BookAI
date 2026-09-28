package com.app.bookai.ai.infrastructure.tool.dto.barber;

import java.time.LocalDate;
import java.time.LocalTime;

public record WorkingHourOverrideToolResponse(
     String reason,
     LocalDate date,
     LocalTime startTime,
     LocalTime endTime
){
}

package com.app.bookai.ai.infrastructure.tool.dto.barber;

import java.time.LocalDate;

public record DayOffToolRequest(
        LocalDate date,
        String reason
){
}

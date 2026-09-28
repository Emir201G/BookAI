package com.app.bookai.ai.infrastructure.tool.dto.barber;

import java.time.LocalDate;

public record DayOffToolResponse(
        LocalDate date,
        String reason
) {
}

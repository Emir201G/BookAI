package com.app.bookai.ai.infrastructure.tool.dto.appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record AppointmentToolRequest(
        LocalDate date,
        LocalTime startTime,
        List<String> treatments
) {
}

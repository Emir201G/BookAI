package com.app.bookai.appointment.domain.port.in;

import java.time.LocalDate;
import java.time.LocalTime;

public interface CheckAvailabilityUseCase {

    boolean isAvailable(
            Long barberId,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime
    );
}
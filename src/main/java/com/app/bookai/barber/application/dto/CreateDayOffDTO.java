package com.app.bookai.barber.application.dto;

import com.app.bookai.barber.domain.model.DayOff;

import java.time.LocalDate;

public record CreateDayOffDTO(
        LocalDate date,
        String reason) {
}

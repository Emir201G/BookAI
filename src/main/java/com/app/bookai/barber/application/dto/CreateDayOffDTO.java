package com.app.bookai.barber.application.dto;

import com.app.bookai.barber.domain.model.DayOff;

import java.time.LocalDate;
import java.util.List;

public record CreateDayOffDTO(
        LocalDate date,
        String reason) {
}

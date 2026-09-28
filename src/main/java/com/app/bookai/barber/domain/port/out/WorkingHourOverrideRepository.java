package com.app.bookai.barber.domain.port.out;

import com.app.bookai.barber.domain.model.WorkingHourOverride;

import java.time.LocalDate;
import java.util.Optional;

public interface WorkingHourOverrideRepository {

    Optional<WorkingHourOverride> findByBarberIdAndDate(
            Long barberId,
            LocalDate date
    );
}
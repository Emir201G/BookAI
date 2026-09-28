package com.app.bookai.barber.domain.port.out;

import com.app.bookai.barber.domain.model.WorkingHour;

import java.time.DayOfWeek;
import java.util.List;

public interface WorkingHourRepository {
    List<WorkingHour> findByBarberIdAndDayOfWeek(
            Long barberId,
            DayOfWeek dayOfWeek
    );
}

package com.app.bookai.barber.domain.port.in;

import com.app.bookai.barber.domain.model.WorkingHour;

import java.util.List;

public interface GetWorkingHoursByBarberUseCase {
    List<WorkingHour> getWorkingHourOfBarber(String name);
}

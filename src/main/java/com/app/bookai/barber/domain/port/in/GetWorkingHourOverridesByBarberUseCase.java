package com.app.bookai.barber.domain.port.in;

import com.app.bookai.barber.domain.model.WorkingHourOverride;

import java.util.List;

public interface GetWorkingHourOverridesByBarberUseCase {
    List<WorkingHourOverride> getWorkingHourOverridesByBarber(String name);
}

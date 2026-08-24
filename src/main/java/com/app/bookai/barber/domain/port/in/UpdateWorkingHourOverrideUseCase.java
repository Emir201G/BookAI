package com.app.bookai.barber.domain.port.in;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.WorkingHourOverride;

import java.time.LocalDate;

public interface UpdateWorkingHourOverrideUseCase {
    Barber updateWorkingHourOverride(LocalDate date, String name, WorkingHourOverride workingHourOverride);
}

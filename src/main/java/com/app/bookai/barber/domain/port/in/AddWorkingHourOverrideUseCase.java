package com.app.bookai.barber.domain.port.in;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.WorkingHour;
import com.app.bookai.barber.domain.model.WorkingHourOverride;

public interface AddWorkingHourOverrideUseCase {
    Barber addWorkingHourOverride(String name, WorkingHourOverride workingHourOverride);
}

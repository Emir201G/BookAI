package com.app.bookai.barber.domain.port.in;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.WorkingHourOverride;

import java.util.List;

public interface CreateWorkingHourOverrideUseCase {
    Barber createWorkingHourOverride(List<WorkingHourOverride> workingHourOverrides, String name);
}

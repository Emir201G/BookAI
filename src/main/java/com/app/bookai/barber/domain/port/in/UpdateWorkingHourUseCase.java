package com.app.bookai.barber.domain.port.in;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.WorkingHour;

import java.util.List;

public interface UpdateWorkingHourUseCase {
    List<WorkingHour> update(String name, List<WorkingHour> workingHours);
}

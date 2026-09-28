package com.app.bookai.barber.domain.port.in;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.WorkingHour;

public interface AddWorkingHourUseCase {

    Barber addWorkingHour(String name, WorkingHour workingHour);
}

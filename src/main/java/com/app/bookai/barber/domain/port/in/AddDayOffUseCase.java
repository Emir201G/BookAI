package com.app.bookai.barber.domain.port.in;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.DayOff;

public interface AddDayOffUseCase {
    Barber addDayOff(String name, DayOff dayOff);
}

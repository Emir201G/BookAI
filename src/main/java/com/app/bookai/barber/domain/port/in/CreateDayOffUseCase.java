package com.app.bookai.barber.domain.port.in;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.DayOff;

import java.util.List;

public interface CreateDayOffUseCase {
    Barber createDayOff(List<DayOff> dayOffs, String name);
}

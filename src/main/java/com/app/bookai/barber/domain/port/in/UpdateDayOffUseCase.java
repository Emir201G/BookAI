package com.app.bookai.barber.domain.port.in;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.DayOff;

import java.time.LocalDate;
import java.util.List;

public interface UpdateDayOffUseCase {
    Barber updateDayOff(String name, LocalDate date, DayOff dayOffs);
}

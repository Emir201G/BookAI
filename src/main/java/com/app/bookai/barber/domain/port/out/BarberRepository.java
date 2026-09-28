package com.app.bookai.barber.domain.port.out;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.DayOff;
import com.app.bookai.barber.domain.model.WorkingHour;
import com.app.bookai.barber.domain.model.WorkingHourOverride;
import com.app.bookai.barber.infrastructure.persistence.entity.BarberEntity;

import java.util.List;
import java.util.Optional;

public interface BarberRepository {
    Barber save(Barber barber);
    void remove(Barber barber);
    List<Barber> getAll();
    Barber update(Barber barber);
    void updateWorkingHours(String phoneNumber, List<WorkingHour> workingHours);
    Optional<Barber> findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<Barber> findByName(String name);
    boolean existsByName(String name);
    Barber addWorkingHour(String name, WorkingHour workingHour);
    Barber addDayOff(String name, DayOff dayOff);
    Barber addWorkingHourOverride(String name, WorkingHourOverride workingHourOverride);
    List<WorkingHour> getWorkingHours(String name);
    List<DayOff> getDayOffs(String name);
    List<WorkingHourOverride> getWorkingHourOverride(String name);
}

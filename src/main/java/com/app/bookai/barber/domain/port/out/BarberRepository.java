package com.app.bookai.barber.domain.port.out;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.WorkingHour;

import java.util.List;

public interface BarberRepository {
    Barber save(Barber barber);
    void remove(Barber barber);
    List<Barber> getAll();
    Barber getBarber(String phoneNumber);
    Barber update(Barber barber);
    void updateWorkingHours(String phoneNumber, List<WorkingHour> workingHours);
    
}

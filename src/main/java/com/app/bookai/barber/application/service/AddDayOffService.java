package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.DayOff;
import com.app.bookai.barber.domain.port.in.AddDayOffUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddDayOffService implements AddDayOffUseCase {
    private final BarberRepository barberRepository;
    @Override
    public Barber addDayOff(String name, DayOff dayOff) {
        return barberRepository.addDayOff(name,dayOff);
    }
}

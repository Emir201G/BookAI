package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.WorkingHourOverride;
import com.app.bookai.barber.domain.port.in.AddWorkingHourOverrideUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddWorkingHourOverrideService implements AddWorkingHourOverrideUseCase {
    private final BarberRepository barberRepository;
    @Override
    public Barber addWorkingHourOverride(String name, WorkingHourOverride workingHourOverride) {
        return barberRepository.addWorkingHourOverride(name, workingHourOverride);
    }
}

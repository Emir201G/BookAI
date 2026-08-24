package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.exception.EmptyDayOffListException;
import com.app.bookai.barber.domain.exception.EmptyWorkingHourOverrideListException;
import com.app.bookai.barber.domain.exception.NotFoundByName;
import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.WorkingHourOverride;
import com.app.bookai.barber.domain.port.in.CreateWorkingHourOverrideUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateWorkingHourOverrideService implements CreateWorkingHourOverrideUseCase {
    private final BarberRepository barberRepository;

    @Override
    @Transactional
    public Barber createWorkingHourOverride(List<WorkingHourOverride> workingHourOverrides, String name) {

        if (workingHourOverrides.isEmpty()) {
            throw new EmptyWorkingHourOverrideListException();
        }
        Barber barber = barberRepository.findByName(name)
                .orElseThrow(() -> new NotFoundByName(name));

        barber.updateWorkingHoursOverride(workingHourOverrides);
        return barberRepository.save(barber);
    }
}

package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.exception.EmptyWorkingHourOverrideListException;
import com.app.bookai.barber.domain.exception.NotFoundByNameException;
import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.WorkingHourOverride;
import com.app.bookai.barber.domain.port.in.UpdateWorkingHourOverrideUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UpdateWorkingHourOverrideService implements UpdateWorkingHourOverrideUseCase {
    private final BarberRepository barberRepository;

    @Override
    @Transactional
    public Barber updateWorkingHourOverride(LocalDate date, String name, WorkingHourOverride workingHourOverride) {

        if (workingHourOverride == null) {
            throw new EmptyWorkingHourOverrideListException();
        }
        Barber barber = barberRepository.findByName(name)
                .orElseThrow(() -> new NotFoundByNameException(name));

        if(barber.getWorkingHourOverrides() != null) {
            for(WorkingHourOverride existingWorkingHourOverride : barber.getWorkingHourOverrides()) {
                if(existingWorkingHourOverride.getDate().equals(date)) {
                    barber.getWorkingHourOverrides().remove(existingWorkingHourOverride);
                    barber.getWorkingHourOverrides().add(workingHourOverride);
                    barberRepository.save(barber);
                }
            }
        }else{
            throw new EmptyWorkingHourOverrideListException();
        }
        return null;
    }
}

package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.exception.EmptyWorkingHourListException;
import com.app.bookai.barber.domain.exception.NotFoundByName;
import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.WorkingHour;
import com.app.bookai.barber.domain.port.in.UpdateWorkingHourUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateWorkingHourService implements UpdateWorkingHourUseCase {
    private final BarberRepository barberRepository;

    @Override
    @Transactional
    public Barber update(String name, List<WorkingHour> workingHours) {
        if (workingHours.isEmpty()) {
            throw new EmptyWorkingHourListException();
        }
        List<WorkingHour> workingHoursCopy = new ArrayList<>(workingHours);
        workingHoursCopy.addAll(workingHours);

        Barber barber = barberRepository.findByName(name)
                .orElseThrow(() -> new NotFoundByName(name));

        barber.setWorkingHours(workingHoursCopy);

        return barberRepository.save(barber);
    }
}

package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.exception.NotFoundByNameException;
import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.WorkingHour;
import com.app.bookai.barber.domain.port.in.AddWorkingHourUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddWorkingHourService implements AddWorkingHourUseCase {

    private final BarberRepository barberRepository;

    @Transactional
    @Override
    public Barber addWorkingHour(String name, WorkingHour workingHour) {

        return barberRepository.addWorkingHour(name, workingHour);
    }
}

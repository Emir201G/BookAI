package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.model.WorkingHour;
import com.app.bookai.barber.domain.port.in.GetWorkingHoursByBarberUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GetWorkingHourOfBarberService implements
        GetWorkingHoursByBarberUseCase {

    private final BarberRepository barberRepository;
    @Override
    public List<WorkingHour> getWorkingHourOfBarber(String name) {
        return barberRepository.getWorkingHours(name);
    }
}

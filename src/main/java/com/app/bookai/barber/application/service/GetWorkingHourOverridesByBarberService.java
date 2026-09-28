package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.model.WorkingHourOverride;
import com.app.bookai.barber.domain.port.in.GetWorkingHourOverridesByBarberUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GetWorkingHourOverridesByBarberService implements
        GetWorkingHourOverridesByBarberUseCase {

    private final BarberRepository barberRepository;
    @Override
    public List<WorkingHourOverride> getWorkingHourOverridesByBarber(String name) {
        return barberRepository
                .getWorkingHourOverride(name);
    }
}

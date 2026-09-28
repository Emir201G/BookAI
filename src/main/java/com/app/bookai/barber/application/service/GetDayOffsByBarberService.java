package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.model.DayOff;
import com.app.bookai.barber.domain.port.in.GetDayOffsByBarberUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GetDayOffsByBarberService implements
        GetDayOffsByBarberUseCase {

    private final BarberRepository barberRepository;
    @Override
    public List<DayOff> getDayOffsByBarber(String name) {
        return barberRepository
                .getDayOffs(name);
    }
}

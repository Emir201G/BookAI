package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.exception.EmptyDayOffListException;
import com.app.bookai.barber.domain.exception.NotFoundByNameException;
import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.DayOff;
import com.app.bookai.barber.domain.port.in.CreateDayOffUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateDayOffService implements CreateDayOffUseCase {
    private final BarberRepository barberRepository;


    @Override
    @Transactional
    public Barber createDayOff(List<DayOff> dayOffs, String name) {
        if (dayOffs == null || dayOffs.size() == 0) {
            throw new EmptyDayOffListException();
        }
        List<DayOff> newDayOffs = new ArrayList<>();
        newDayOffs.addAll(dayOffs);

        Barber barber = barberRepository.findByName(name)
                .orElseThrow(() -> new NotFoundByNameException(name));

        barber.setDayOffs(newDayOffs);

        return barberRepository.save(barber);
    }
}

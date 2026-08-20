package com.app.bookai.barber.application.service;

import com.app.bookai.barber.domain.exception.EmptyDayOffListException;
import com.app.bookai.barber.domain.exception.NotFoundByName;
import com.app.bookai.barber.domain.exception.NotFundByDate;
import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.DayOff;
import com.app.bookai.barber.domain.port.in.UpdateDayOffUseCase;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateDayOffService implements UpdateDayOffUseCase {
    private final BarberRepository barberRepository;

    @Override
    @Transactional
    public Barber updateDayOff(
            String name,
            LocalDate date,
            DayOff updatedDayOff
    ) {

        Barber barber = barberRepository.findByName(name)
                .orElseThrow(() -> new NotFoundByName(name));

        if (barber.getDayOffs() == null || barber.getDayOffs().isEmpty()) {
            throw new EmptyDayOffListException();
        }

        DayOff dayOff = barber.getDayOffs()
                .stream()
                .filter(d -> d.getDate().equals(date))
                .findFirst()
                .orElseThrow(() -> new NotFundByDate(date));

        dayOff.setDate(updatedDayOff.getDate());
        dayOff.setReason(updatedDayOff.getReason());

        return barberRepository.save(barber);
    }
}

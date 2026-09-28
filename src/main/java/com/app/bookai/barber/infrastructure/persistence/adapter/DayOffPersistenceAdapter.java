package com.app.bookai.barber.infrastructure.persistence.adapter;

import com.app.bookai.barber.domain.port.out.DayOffRepository;
import com.app.bookai.barber.infrastructure.persistence.repository.JpaDayOffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
@Repository
@RequiredArgsConstructor
public class DayOffPersistenceAdapter implements DayOffRepository {
    private final JpaDayOffRepository jpaDayOffRepository;
    @Override
    public boolean existsByBarberIdAndDate(
            Long barberId,
            LocalDate date
    ) {
        return jpaDayOffRepository
                .existsByBarber_IdAndDate(barberId, date);
    }
}

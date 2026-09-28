package com.app.bookai.barber.infrastructure.persistence.adapter;

import com.app.bookai.barber.domain.model.WorkingHourOverride;
import com.app.bookai.barber.domain.port.out.WorkingHourOverrideRepository;
import com.app.bookai.barber.infrastructure.persistence.mapper.WorkingHourOverridePersistenceMapper;
import com.app.bookai.barber.infrastructure.persistence.repository.JpaWorkingHourOverride;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WorkingHourOverridePersistenceAdapter implements WorkingHourOverrideRepository {

    private final JpaWorkingHourOverride jpaWorkingHourOverride;
    private final WorkingHourOverridePersistenceMapper workingHourOverridePersistenceMapper;

    @Override
    public Optional<WorkingHourOverride> findByBarberIdAndDate(Long barberId, LocalDate date) {
        return jpaWorkingHourOverride
                .findByBarber_IdAndDate(barberId, date)
                .map(workingHourOverridePersistenceMapper::toDomain);
    }
}

package com.app.bookai.barber.infrastructure.persistence.adapter;

import com.app.bookai.barber.domain.model.WorkingHour;
import com.app.bookai.barber.domain.port.out.WorkingHourRepository;
import com.app.bookai.barber.infrastructure.persistence.mapper.WorkingHourPersistenceMapper;
import com.app.bookai.barber.infrastructure.persistence.repository.JpaWorkingHour;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WorkingHourPersistenceAdapter implements WorkingHourRepository {

    private final JpaWorkingHour jpaWorkingHour;
    private final WorkingHourPersistenceMapper workingHourPersistenceMapper;

    @Override
    public List<WorkingHour> findByBarberIdAndDayOfWeek(Long barberId, DayOfWeek dayOfWeek) {
        return jpaWorkingHour
                .findByBarber_IdAndDayOfWeek(barberId, dayOfWeek)
                .stream()
                .map(workingHourPersistenceMapper::toDomain)
                .toList();
    }
}

package com.app.bookai.barber.infrastructure.persistence.repository;

import com.app.bookai.barber.infrastructure.persistence.entity.WorkingHourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface JpaWorkingHour extends JpaRepository<WorkingHourEntity, Long> {
    List<WorkingHourEntity> findByBarber_IdAndDayOfWeek(
            Long barberId,
            DayOfWeek dayOfWeek
    );
}

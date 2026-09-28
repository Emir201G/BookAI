package com.app.bookai.barber.infrastructure.persistence.repository;

import com.app.bookai.barber.infrastructure.persistence.entity.WorkingHourOverrideEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface JpaWorkingHourOverride extends JpaRepository<WorkingHourOverrideEntity, Long> {

    Optional<WorkingHourOverrideEntity> findByBarber_IdAndDate(Long barberId, LocalDate date);
}

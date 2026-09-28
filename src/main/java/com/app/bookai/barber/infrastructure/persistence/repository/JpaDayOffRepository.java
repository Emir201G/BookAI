package com.app.bookai.barber.infrastructure.persistence.repository;

import com.app.bookai.barber.infrastructure.persistence.entity.DayOffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface JpaDayOffRepository extends JpaRepository<DayOffEntity, Long> {
    boolean existsByBarber_IdAndDate(Long barberId, LocalDate date);

}

package com.app.bookai.appointment.infrastructure.persistence.repository;

import com.app.bookai.appointment.infrastructure.persistence.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface JpaAppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    @Query("""
        SELECT COUNT(a) > 0
        FROM AppointmentEntity a
        WHERE a.barberId = :barberId
          AND a.date = :date
          AND a.startTime < :endTime
          AND a.endTime > :startTime
    """)
    boolean existsOverlappingAppointment(
            @Param("barberId") Long barberId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );

    Optional<AppointmentEntity> findByCustomerIdAndDateAndStartTime(
            Long customerId,
            LocalDate date,
            LocalTime startTime
    );
}

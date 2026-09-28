package com.app.bookai.barber.infrastructure.persistence.repository;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.WorkingHour;
import com.app.bookai.barber.infrastructure.persistence.entity.BarberEntity;
import com.app.bookai.barber.infrastructure.persistence.entity.DayOffEntity;
import com.app.bookai.barber.infrastructure.persistence.entity.WorkingHourEntity;
import com.app.bookai.barber.infrastructure.persistence.entity.WorkingHourOverrideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaBarberRepository extends JpaRepository<BarberEntity, Long> {
    Optional<BarberEntity> findByPhoneNumber(String barberNumber);
    boolean existsByPhoneNumber(String barberNumber);
    Optional<BarberEntity> findByName(String name);
    boolean existsByName(String name);
    @Query("""
    SELECT wh
    FROM BarberEntity b
    JOIN b.workingHours wh
    WHERE b.name = :name
""")
    List<WorkingHourEntity> findWorkingHoursByBarberName(@Param("name") String name);

    @Query("""
    SELECT d
    FROM BarberEntity b
    JOIN b.dayOffs d
    WHERE b.name = :name
""")
    List<DayOffEntity> findDayOffsByBarberName(@Param("name") String name);

    @Query("""
    SELECT w
    FROM BarberEntity b
    JOIN b.workingHourOverrides w
    WHERE b.name = :name
""")
    List<WorkingHourOverrideEntity> findWorkingHourOverridesByBarberName(
            @Param("name") String name
    );
}

package com.app.bookai.barber.infrastructure.persistence.repository;

import com.app.bookai.barber.infrastructure.persistence.entity.WorkingHourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWorkingHourRepository extends JpaRepository<WorkingHourEntity, Long> {
}

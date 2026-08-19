package com.app.bookai.barber.infrastructure.persistence.repository;

import com.app.bookai.barber.infrastructure.persistence.entity.DayOffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDayOffRepository extends JpaRepository<DayOffEntity,Long> {
}

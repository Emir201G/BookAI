package com.app.bookai.barber.infrastructure.persistence.repository;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.infrastructure.persistence.entity.BarberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaBarberRepository extends JpaRepository<BarberEntity, Long> {
    Optional<BarberEntity> findByPhoneNumber(String barberNumber);
    boolean existsByPhoneNumber(String barberNumber);
    Optional<BarberEntity> findByName(String name);
    boolean existsByName(String name);


}

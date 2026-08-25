package com.app.bookai.appointment.infrastructure.persistence.repository;

import com.app.bookai.appointment.infrastructure.persistence.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
}

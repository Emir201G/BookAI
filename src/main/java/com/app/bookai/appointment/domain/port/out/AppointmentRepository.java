package com.app.bookai.appointment.domain.port.out;

import com.app.bookai.appointment.domain.model.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);
    boolean existsOverlappingAppointment(
            Long barberId,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime
    );

    Optional<Appointment> findByCustomerIdAndDateAndStartTime(
            Long customerId,
            LocalDate date,
            LocalTime startTime
    );

    List<Appointment> getAllAppointment();

    Optional<Appointment> findById(Long id);
}

package com.app.bookai.appointment.application.dto;

import com.app.bookai.appointment.domain.enums.AppointmentStatus;
import com.app.bookai.appointment.domain.model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record AppointmentResponseDTO(
        Long id,
        Long barberId,
        Long customerId,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        AppointmentStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<AppointmentTreatmentResponseDTO> treatments
) {
}

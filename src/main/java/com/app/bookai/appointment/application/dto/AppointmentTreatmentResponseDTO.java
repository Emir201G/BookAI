package com.app.bookai.appointment.application.dto;

import java.math.BigDecimal;

public record AppointmentTreatmentResponseDTO(
        Long id,
        Long treatmentId,
        BigDecimal price,
        Integer durationMinutes
) {
}
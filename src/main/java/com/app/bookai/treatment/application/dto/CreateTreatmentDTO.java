package com.app.bookai.treatment.application.dto;

import java.math.BigDecimal;

public record CreateTreatmentDTO(
        String name,
        BigDecimal price,
        Integer durationMinutes

) {
}

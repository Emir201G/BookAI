package com.app.bookai.treatment.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TreatmentResponseDTO(
        Long id,
        String name,
        BigDecimal price,
        Integer durationMinutes,
        Boolean isActive
) {
}

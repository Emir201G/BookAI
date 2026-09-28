package com.app.bookai.ai.infrastructure.tool.dto.treatment;

import java.math.BigDecimal;

public record TreatmentToolResponse (
        Long id,
        String name,
        BigDecimal price,
        Integer durationMinutes
){
}

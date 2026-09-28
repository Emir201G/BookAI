package com.app.bookai.ai.infrastructure.tool.dto.treatment;

import org.springframework.ai.tool.annotation.ToolParam;

import java.math.BigDecimal;

public record CreateTreatmentToolRequest(

        @ToolParam(description = "Nombre del tratamiento")
        String name,

        @ToolParam(description = "Precio del tratamiento")
        BigDecimal price,

        @ToolParam(description = "Duración del tratamiento en minutos")
        Integer durationMinutes
) {
}
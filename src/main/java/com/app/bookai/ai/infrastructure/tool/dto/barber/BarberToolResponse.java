package com.app.bookai.ai.infrastructure.tool.dto.barber;

public record BarberToolResponse(
        boolean success,
        String message,
        String name,
        String phoneNumber
) {
}

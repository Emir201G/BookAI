package com.app.bookai.ai.infrastructure.tool.dto.customer;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record CustomerToolResponse(
        String name,
        String phoneNumber,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

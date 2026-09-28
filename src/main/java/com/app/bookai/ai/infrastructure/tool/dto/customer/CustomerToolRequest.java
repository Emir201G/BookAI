package com.app.bookai.ai.infrastructure.tool.dto.customer;

import com.app.bookai.shared.enums.RoleType;

import java.time.LocalTime;

public record CustomerToolRequest(
        String name,
        String phoneNumber
) {
}

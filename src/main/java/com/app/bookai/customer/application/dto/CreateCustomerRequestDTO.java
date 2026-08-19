package com.app.bookai.customer.application.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequestDTO(
        @NotBlank String name,
        @NotBlank String phoneNumber) {
}

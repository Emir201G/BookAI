package com.app.bookai.barber.application.dto;

import com.app.bookai.barber.domain.model.WorkingHour;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UpdateBarberRequestDTO(
        @NotBlank String name,
        @NotBlank String phoneNumber,
        @NotBlank List<WorkingHour> workingHours

) {
}

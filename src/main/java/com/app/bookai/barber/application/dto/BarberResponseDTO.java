package com.app.bookai.barber.application.dto;

import com.app.bookai.barber.domain.model.DayOff;
import com.app.bookai.barber.domain.model.WorkingHour;
import com.app.bookai.barber.domain.model.WorkingHourOverride;

import java.util.List;

public record BarberResponseDTO(
        Long id,
        String name,
        String phoneNumber,
        boolean isActive,
        List<WorkingHour> workingHours,
        List<DayOff> dayOffs,
        List<WorkingHourOverride> workingHourOverrides
) {
}

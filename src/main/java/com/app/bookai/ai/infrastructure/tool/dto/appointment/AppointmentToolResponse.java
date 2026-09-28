package com.app.bookai.ai.infrastructure.tool.dto.appointment;

import com.app.bookai.appointment.domain.model.AppointmentTreatment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record AppointmentToolResponse (
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        List<AppointmentTreatment> treatments
){
}

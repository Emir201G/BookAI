package com.app.bookai.appointment.application.dto;

import com.app.bookai.appointment.domain.model.AppointmentTreatment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record CreateAppointmentDTO(

        LocalDate date,
        LocalTime startTime,
        List<String> treatments

) {
}

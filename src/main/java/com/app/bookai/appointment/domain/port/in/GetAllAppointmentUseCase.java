package com.app.bookai.appointment.domain.port.in;

import com.app.bookai.appointment.domain.model.Appointment;

import java.util.List;

public interface GetAllAppointmentUseCase {
    List<Appointment> getAllAppointment();
}

package com.app.bookai.appointment.domain.port.in;

import com.app.bookai.appointment.application.dto.CreateAppointmentDTO;
import com.app.bookai.appointment.domain.model.Appointment;

public interface CreateWithoutBarberUseCase {
    Appointment create(CreateAppointmentDTO createAppointmentDTO, String customerPhone);
}

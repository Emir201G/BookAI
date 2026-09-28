package com.app.bookai.appointment.domain.port.in;

import com.app.bookai.appointment.domain.model.Appointment;

public interface FindByIdUseCase {
    Appointment findById(Long id);
}

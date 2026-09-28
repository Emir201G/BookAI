package com.app.bookai.appointment.domain.port.in;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface CancelAppointmentUseCase {
    void cancelAppointment(String customerPhone, LocalDate date, LocalTime localDateTime);
}

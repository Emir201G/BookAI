package com.app.bookai.appointment.application.service;

import com.app.bookai.appointment.domain.model.Appointment;
import com.app.bookai.appointment.domain.port.in.GetAllAppointmentUseCase;
import com.app.bookai.appointment.domain.port.out.AppointmentRepository;
import com.app.bookai.appointment.infrastructure.persistence.mapper.AppointmentPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllAppointmentService implements GetAllAppointmentUseCase {

    private final AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> getAllAppointment() {
        return appointmentRepository.getAllAppointment();
    }
}

package com.app.bookai.appointment.application.service;

import com.app.bookai.appointment.domain.model.Appointment;
import com.app.bookai.appointment.domain.port.in.FindByIdUseCase;
import com.app.bookai.appointment.domain.port.out.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class FindByIdService implements FindByIdUseCase {

    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }
}

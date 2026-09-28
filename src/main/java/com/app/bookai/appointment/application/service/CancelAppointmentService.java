package com.app.bookai.appointment.application.service;

import com.app.bookai.appointment.domain.enums.AppointmentStatus;
import com.app.bookai.appointment.domain.model.Appointment;
import com.app.bookai.appointment.domain.port.in.CancelAppointmentUseCase;
import com.app.bookai.appointment.domain.port.out.AppointmentRepository;
import com.app.bookai.customer.domain.model.Customer;
import com.app.bookai.customer.domain.port.out.CustomerRepository;
import com.app.bookai.shared.exception.NotFoundByPhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class CancelAppointmentService implements CancelAppointmentUseCase {

    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public void cancelAppointment(String customerPhone, LocalDate date, LocalTime startTime) {

        Customer customer = customerRepository.findByPhoneNumber(customerPhone);

        Appointment appointment = appointmentRepository.findByCustomerIdAndDateAndStartTime(
                        customer.getId(),
                        date,
                        startTime)
                .orElseThrow(() ->
                        new RuntimeException("appointment not found"));

        appointment.updateStatus(AppointmentStatus.CANCELLED);

        appointmentRepository.save(appointment);
    }
}

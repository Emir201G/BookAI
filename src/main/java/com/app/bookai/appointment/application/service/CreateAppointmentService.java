package com.app.bookai.appointment.application.service;

import com.app.bookai.appointment.application.dto.CreateAppointmentDTO;
import com.app.bookai.appointment.domain.enums.AppointmentStatus;
import com.app.bookai.appointment.domain.model.Appointment;
import com.app.bookai.appointment.domain.model.AppointmentTreatment;
import com.app.bookai.appointment.domain.port.in.CheckAvailabilityUseCase;
import com.app.bookai.appointment.domain.port.in.CreateAppointmentUseCase;
import com.app.bookai.appointment.domain.port.out.AppointmentRepository;
import com.app.bookai.barber.domain.exception.NotFoundByNameException;
import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import com.app.bookai.customer.domain.model.Customer;
import com.app.bookai.customer.domain.port.out.CustomerRepository;
import com.app.bookai.shared.exception.NotFoundByPhoneNumber;
import com.app.bookai.treatment.domain.exception.NotFoundByNameTreatmentException;
import com.app.bookai.treatment.domain.model.Treatment;
import com.app.bookai.treatment.domain.port.out.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateAppointmentService implements CreateAppointmentUseCase {

    private final AppointmentRepository appointmentRepository;
    private final BarberRepository barberRepository;
    private final TreatmentRepository treatmentRepository;
    private final CustomerRepository customerRepository;
    private final CheckAvailabilityUseCase checkAvailabilityUseCase;

    @Override
    public Appointment create(
            CreateAppointmentDTO request,
            String barberName,
            String customerPhone
    ) {

        Customer customer = customerRepository
                .findByPhoneNumber(customerPhone);

        Barber barber = barberRepository
                .findByName(barberName)
                .orElseThrow(() ->
                        new NotFoundByNameException(barberName)
                );

        List<AppointmentTreatment> treatments =
                request.treatments()
                        .stream()
                        .map(name -> {

                            Treatment treatment = treatmentRepository
                                    .findByName(name)
                                    .orElseThrow(() ->
                                            new NotFoundByNameTreatmentException(name)
                                    );

                            return AppointmentTreatment.builder()
                                    .treatmentId(treatment.getId())
                                    .price(treatment.getPrice())
                                    .durationMinutes(
                                            treatment.getDurationMinutes()
                                    )
                                    .build();
                        })
                        .toList();

        int totalDuration = treatments.stream()
                .mapToInt(AppointmentTreatment::getDurationMinutes)
                .sum();

        LocalTime endTime = request.startTime()
                .plusMinutes(totalDuration);

        boolean available = checkAvailabilityUseCase.isAvailable(
                barber.getId(),
                request.date(),
                request.startTime(),
                endTime
        );

        if (!available) {
            throw new RuntimeException(
                    "Barber is not available at this time."
            );
        }

        Appointment appointment = Appointment.builder()
                .barberId(barber.getId())
                .customerId(customer.getId())
                .date(request.date())
                .startTime(request.startTime())
                .endTime(endTime)
                .status(AppointmentStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .treatments(treatments)
                .build();

        return appointmentRepository.save(appointment);
    }
}
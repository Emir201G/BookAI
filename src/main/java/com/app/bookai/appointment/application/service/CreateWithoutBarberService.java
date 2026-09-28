package com.app.bookai.appointment.application.service;

import com.app.bookai.appointment.application.dto.CreateAppointmentDTO;
import com.app.bookai.appointment.domain.enums.AppointmentStatus;
import com.app.bookai.appointment.domain.model.Appointment;
import com.app.bookai.appointment.domain.model.AppointmentTreatment;
import com.app.bookai.appointment.domain.port.in.CheckAvailabilityUseCase;
import com.app.bookai.appointment.domain.port.in.CreateWithoutBarberUseCase;
import com.app.bookai.appointment.domain.port.out.AppointmentRepository;
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
public class CreateWithoutBarberService implements CreateWithoutBarberUseCase {

    private final CustomerRepository customerRepository;
    private final BarberRepository barberRepository;
    private final TreatmentRepository treatmentRepository;
    private final AppointmentRepository appointmentRepository;
    private final CheckAvailabilityUseCase checkAvailabilityUseCase;

    @Override
    public Appointment create(CreateAppointmentDTO createAppointmentDTO,
            String customerPhone
    ) {

        Customer customer = customerRepository
                .findByPhoneNumber(customerPhone);

        List<AppointmentTreatment> treatments =
                createAppointmentDTO.treatments()
                        .stream()
                        .map(name -> {

                            Treatment treatment = treatmentRepository
                                    .findByName(name)
                                    .orElseThrow(() ->
                                            new NotFoundByNameTreatmentException(name));

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

        LocalTime endTime = createAppointmentDTO
                .startTime()
                .plusMinutes(totalDuration);

        Barber availableBarber = barberRepository
                .getAll()
                .stream()
                .filter(Barber::getIsActive)
                .filter(barber ->
                        checkAvailabilityUseCase.
                                isAvailable(
                                        barber.getId(),
                                        createAppointmentDTO.date(),
                                        createAppointmentDTO.startTime(),
                                        endTime
                                )
                )
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "No Barber is available at this time"
                        )
                );


        Appointment appointment = Appointment.builder()
                .barberId(availableBarber.getId())
                .customerId(customer.getId())
                .date(createAppointmentDTO.date())
                .startTime(createAppointmentDTO.startTime())
                .endTime(endTime)
                .status(AppointmentStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .treatments(treatments)
                .build();

        return appointmentRepository.save(appointment);
    }
}

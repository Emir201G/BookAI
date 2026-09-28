package com.app.bookai.appointment.infrastructure.controller;

import com.app.bookai.appointment.application.dto.AppointmentResponseDTO;
import com.app.bookai.appointment.application.dto.CreateAppointmentDTO;
import com.app.bookai.appointment.application.mapper.AppointmentMapper;
import com.app.bookai.appointment.domain.model.Appointment;
import com.app.bookai.appointment.domain.port.in.CancelAppointmentUseCase;
import com.app.bookai.appointment.domain.port.in.CreateAppointmentUseCase;
import com.app.bookai.appointment.domain.port.in.CreateWithoutBarberUseCase;
import com.app.bookai.appointment.domain.port.in.GetAllAppointmentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appointment")
public class AppointmentController {

    private final CreateAppointmentUseCase createAppointmentUseCase;
    private final CreateWithoutBarberUseCase createWithoutBarberUseCase;
    private final CancelAppointmentUseCase cancelAppointmentUseCase;
    private final GetAllAppointmentUseCase getAllAppointmentUseCase;
    private final AppointmentMapper appointmentMapper;

    @PostMapping("/create/{name}/{customerPhone}")
    public ResponseEntity<AppointmentResponseDTO> createAppointmentTreatment(
            @RequestBody CreateAppointmentDTO createAppointmentDTO,
            @PathVariable String name,
            @PathVariable String customerPhone
    ) {

        Appointment appointment = createAppointmentUseCase.create(createAppointmentDTO, name, customerPhone);

        return ResponseEntity.ok(appointmentMapper.toResponseDTO(appointment));
    }

    @PostMapping("/create/with-out-barber/{customerPhone}")
    public ResponseEntity<AppointmentResponseDTO> createWithOutBarberAppointmentTreatment(
            @RequestBody CreateAppointmentDTO createAppointmentDTO,
            @PathVariable String customerPhone
    ) {

        Appointment appointment = createWithoutBarberUseCase.create(createAppointmentDTO, customerPhone);

        return ResponseEntity.ok(appointmentMapper.toResponseDTO(appointment));
    }


    @PostMapping("/cancel/{customerPhone}/{date}/{startTime}")
    public ResponseEntity<?> cancelAppointmentTreatment(
            @PathVariable String customerPhone,
            @PathVariable LocalDate date,
            @PathVariable LocalTime startTime
    ) {
        cancelAppointmentUseCase.cancelAppointment(customerPhone, date, startTime);
        return ResponseEntity.ok("cancelled");
    }


    @GetMapping("/all")
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments() {
        List<Appointment> appointments = getAllAppointmentUseCase.getAllAppointment();

        return ResponseEntity.ok(appointmentMapper.toResponseDtoList(appointments));
    }
}

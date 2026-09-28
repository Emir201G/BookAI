package com.app.bookai.ai.infrastructure.tool;

import com.app.bookai.ai.infrastructure.tool.dto.appointment.AppointmentToolRequest;
import com.app.bookai.ai.infrastructure.tool.dto.appointment.AppointmentToolResponse;
import com.app.bookai.ai.infrastructure.tool.mapper.AppointmentToolMapper;
import com.app.bookai.appointment.application.dto.CreateAppointmentDTO;
import com.app.bookai.appointment.domain.model.Appointment;
import com.app.bookai.appointment.domain.port.in.CreateAppointmentUseCase;
import com.app.bookai.appointment.domain.port.in.CreateWithoutBarberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentTool {

    private final CreateWithoutBarberUseCase createWithoutBarberUseCase;
    private final AppointmentToolMapper appointmentToolMapper;

    @Tool(description = """
            Crear un turno/cita.
            """)
    public AppointmentToolResponse createAppointment(
            @ToolParam(description = "Numero del telefono o celular del cliente")
            String phoneNumber,
            @ToolParam(description = "Datos de la cita/turno")
            AppointmentToolRequest appointmentToolRequest
    ) {

        CreateAppointmentDTO createAppointmentDTO = appointmentToolMapper
                .toCreateAppointmentDTO(appointmentToolRequest);

        Appointment appointment = createWithoutBarberUseCase
                .create(
                        createAppointmentDTO,
                        phoneNumber
                );

        return appointmentToolMapper
                .toAppointmentToolResponse(appointment);
    }
}

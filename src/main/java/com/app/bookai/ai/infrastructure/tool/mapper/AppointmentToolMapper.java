package com.app.bookai.ai.infrastructure.tool.mapper;

import com.app.bookai.ai.infrastructure.tool.dto.appointment.AppointmentToolRequest;
import com.app.bookai.ai.infrastructure.tool.dto.appointment.AppointmentToolResponse;
import com.app.bookai.appointment.application.dto.CreateAppointmentDTO;
import com.app.bookai.appointment.domain.model.Appointment;
import com.app.bookai.appointment.domain.model.AppointmentTreatment;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AppointmentToolMapper {

    Appointment toAppointment(AppointmentToolRequest appointmentToolRequest);
    AppointmentToolResponse toAppointmentToolResponse(Appointment appointment);
    CreateAppointmentDTO toCreateAppointmentDTO(AppointmentToolRequest appointmentToolRequest);

    // Método adaptado a los atributos reales de AppointmentTreatment
    default List<AppointmentTreatment> mapTreatments(List<String> treatmentNames) {
        if (treatmentNames == null) {
            return null;
        }
        // Nota: Como AppointmentTreatment maneja IDs y precios,
        // aquí puedes inicializar los campos que necesites con los valores por defecto
        // o resolverlos en tu servicio de negocio antes de guardar el turno.
        return treatmentNames.stream()
                .map(name -> AppointmentTreatment.builder()
                        // .treatmentId(...) -> Si el string es un ID numérico parseable, puedes convertirlo, o manejarlo en el Service
                        .build())
                .collect(Collectors.toList());
    }
}
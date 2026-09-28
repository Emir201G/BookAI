package com.app.bookai.appointment.application.mapper;

import com.app.bookai.appointment.application.dto.AppointmentResponseDTO;
import com.app.bookai.appointment.application.dto.AppointmentTreatmentResponseDTO;
import com.app.bookai.appointment.application.dto.CreateAppointmentDTO;
import com.app.bookai.appointment.domain.model.Appointment;
import com.app.bookai.appointment.domain.model.AppointmentTreatment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(target = "treatments", ignore = true)
    Appointment toDomain(CreateAppointmentDTO createAppointmentDTO);

    AppointmentResponseDTO toResponseDTO(Appointment appointment);

    AppointmentTreatmentResponseDTO toResponseDTO(
            AppointmentTreatment appointmentTreatment
    );

    List<AppointmentTreatmentResponseDTO> toTreatmentResponseDTOList(
            List<AppointmentTreatment> treatments
    );

    List<AppointmentResponseDTO> toResponseDtoList(List<Appointment> appointments);

}
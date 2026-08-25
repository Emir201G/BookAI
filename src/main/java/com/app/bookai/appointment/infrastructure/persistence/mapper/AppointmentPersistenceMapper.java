package com.app.bookai.appointment.infrastructure.persistence.mapper;

import com.app.bookai.appointment.domain.model.Appointment;
import com.app.bookai.appointment.infrastructure.persistence.entity.AppointmentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentPersistenceMapper {

    AppointmentEntity toEntity(Appointment appointment);

    Appointment toDomain(AppointmentEntity appointmentEntity);
}

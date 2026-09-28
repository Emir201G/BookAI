package com.app.bookai.appointment.infrastructure.persistence.mapper;

import com.app.bookai.appointment.domain.model.Appointment;
import com.app.bookai.appointment.domain.model.AppointmentTreatment;
import com.app.bookai.appointment.infrastructure.persistence.entity.AppointmentEntity;
import com.app.bookai.appointment.infrastructure.persistence.entity.AppointmentTreatmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentPersistenceMapper {

    @Mapping(target = "treatments", source = "treatments")
    AppointmentEntity toEntity(Appointment appointment);

    Appointment toDomain(AppointmentEntity entity);

    @Mapping(target = "appointment", ignore = true)
    AppointmentTreatmentEntity toEntity(
            AppointmentTreatment treatment
    );

    @Mapping(
            target = "appointmentId",
            source = "appointment.id"
    )
    AppointmentTreatment toDomain(
            AppointmentTreatmentEntity entity
    );

    List<Appointment> toDomainList(List<AppointmentEntity> entities);
}

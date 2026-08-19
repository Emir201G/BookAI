package com.app.bookai.barber.infrastructure.persistence.mapper;

import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.WorkingHour;
import com.app.bookai.barber.infrastructure.persistence.entity.BarberEntity;
import com.app.bookai.barber.infrastructure.persistence.entity.WorkingHourEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BarberPersistenceMapper {
    @Mapping(target = "id",source = "id")
    BarberEntity toBarberEntity(Barber barber);
    Barber toDomain(BarberEntity barberEntity);
    List<Barber> toDomain(List<BarberEntity> barberEntities);

    WorkingHourEntity toWorkingHourEntity(WorkingHour workingHour);
    List<WorkingHourEntity> toWorkingHourEntity(List<WorkingHour> workingHours);
}
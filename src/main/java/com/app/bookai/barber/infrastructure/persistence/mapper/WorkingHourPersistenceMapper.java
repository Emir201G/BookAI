package com.app.bookai.barber.infrastructure.persistence.mapper;

import com.app.bookai.barber.domain.model.WorkingHour;
import com.app.bookai.barber.infrastructure.persistence.entity.WorkingHourEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkingHourPersistenceMapper {

    @Mapping(source = "barber.id", target = "barberId")
    WorkingHour toDomain(WorkingHourEntity entity);}

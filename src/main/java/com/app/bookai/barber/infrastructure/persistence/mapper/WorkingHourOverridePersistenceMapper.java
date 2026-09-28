package com.app.bookai.barber.infrastructure.persistence.mapper;

import com.app.bookai.barber.domain.model.WorkingHourOverride;
import com.app.bookai.barber.infrastructure.persistence.entity.WorkingHourOverrideEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkingHourOverridePersistenceMapper {
    @Mapping(source = "barber.id", target = "barberId")
    WorkingHourOverride toDomain(WorkingHourOverrideEntity entity);

    @Mapping(target = "barber", ignore = true)
    WorkingHourOverrideEntity toEntity(WorkingHourOverride domain);
}

package com.app.bookai.treatment.infrastructure.persistence.mapper;

import com.app.bookai.treatment.domain.model.Treatment;
import com.app.bookai.treatment.infrastructure.persistence.entity.TreatmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreatmentPersistenceMapper {
//    @Mapping(target = "durationMinutes",source = "durationMinutes")
    TreatmentEntity toEntity (Treatment treatment);
    Treatment toDomain(TreatmentEntity treatment);

    List<Treatment> toDomain (List<TreatmentEntity> treatments);
}

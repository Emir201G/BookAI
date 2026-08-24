package com.app.bookai.treatment.application.mapper;

import com.app.bookai.treatment.application.dto.CreateTreatmentDTO;
import com.app.bookai.treatment.application.dto.TreatmentResponseDTO;
import com.app.bookai.treatment.domain.model.Treatment;
import com.app.bookai.treatment.domain.port.in.CreateTreatmentUseCase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TreatmentMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", ignore = true)
    Treatment toDomainTreatment(CreateTreatmentDTO createTreatmentDTO);
    TreatmentResponseDTO toTreatmentResponseDTO(Treatment treatment);
}

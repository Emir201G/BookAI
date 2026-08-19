package com.app.bookai.barber.application.mapper;

import com.app.bookai.barber.application.dto.*;
import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.WorkingHour;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BarberMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", expression = "java(com.app.bookai.shared.enums.RoleType.WORKER)")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Barber toDomain(CreateBarberRequestDTO requestDTO);

    BarberResponseDTO toDTO(Barber barber);

    List<BarberResponseDTO> toResponseDTO(List<Barber> barbers);

    Barber toDomain(UpdateBarberRequestDTO requestDTO);

    WorkingHour toDomain(WorkingHourRequestDTO requestDTO);

    List<WorkingHour> toDomain(
            List<WorkingHourRequestDTO> requestDTO
    );
}

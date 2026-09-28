package com.app.bookai.ai.infrastructure.tool.mapper;

import com.app.bookai.ai.infrastructure.tool.dto.treatment.CreateTreatmentToolRequest;
import com.app.bookai.ai.infrastructure.tool.dto.treatment.TreatmentToolResponse;
import com.app.bookai.treatment.domain.model.Treatment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreatmentToolMapper {

    TreatmentToolResponse toResponse(Treatment treatment);
    List<TreatmentToolResponse> toResponseList(List<Treatment> treatments);

    Treatment toModel(CreateTreatmentToolRequest request);
}

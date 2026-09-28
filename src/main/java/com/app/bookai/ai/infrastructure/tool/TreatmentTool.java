package com.app.bookai.ai.infrastructure.tool;

import com.app.bookai.ai.infrastructure.tool.dto.treatment.TreatmentToolResponse;
import com.app.bookai.ai.infrastructure.tool.mapper.TreatmentToolMapper;
import com.app.bookai.treatment.domain.model.Treatment;
import com.app.bookai.treatment.domain.port.in.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TreatmentTool {

    private final GetAllTreatmentUseCase getAllTreatmentUseCase;
    private final CreateTreatmentUseCase createTreatmentUseCase;
    private final UpdatePriceTreatmentUseCase updatePriceTreatmentUseCase;
    private final GetTreatmentByNameUseCase getTreatmentByNameUseCase;
    private final DeleteTreatmentByNameUseCase deleteTreatmentByNameUseCase;
    private final TreatmentToolMapper treatmentToolMapper;

    @Tool(description = "Obtiene la lista de tratamientos disponibles en BookAi")
    public List<TreatmentToolResponse> getAllTreatments() {

        List<Treatment> treatments =
                getAllTreatmentUseCase.getAllTreatment();
        return treatmentToolMapper.toResponseList(treatments);
    }


    @Tool(description = "Crea un nuevo tratamiento en BookAi.")
    public TreatmentToolResponse createTreatment(

            @ToolParam(description = "Nombre del tratamiento")
            String name,

            @ToolParam(description = "Precio del tratamiento")
            BigDecimal price,

            @ToolParam(description = "Duración del tratamiento en minutos")
            Integer durationMinutes) {

        Treatment treatment = new Treatment();

        treatment.setName(name);
        treatment.setPrice(price);
        treatment.setDurationMinutes(durationMinutes);

        Treatment createdTreatment =
                createTreatmentUseCase.createTreatment(treatment);

        return treatmentToolMapper.toResponse(createdTreatment);
    }


    @Tool(description = "Actulizar precio del tratamiento en BookAi.")
    public TreatmentToolResponse updatePriceTreatment(
            @ToolParam(description = "Nombre del tratamiento")
            String name,
            @ToolParam(description = "Precio del tratamiento para actulizar")
            BigDecimal price

    ) {
        Treatment treatment = updatePriceTreatmentUseCase.updatePriceTreatment(name, price);

        return treatmentToolMapper.toResponse(treatment);

    }


    @Tool(description = "Buscar el tratamiento  por el nombre")
    public TreatmentToolResponse getTreatmentByName(
            @ToolParam(description = "Nombre del tratamiento")
            String name
    ) {

        Treatment treatment = getTreatmentByNameUseCase.getTreatmentByName(name);

        return treatmentToolMapper.toResponse(treatment);
    }


    @Tool(description = "Eliminar tratamiento por el nombre")
    public void deleteTreatmentByName(
            @ToolParam(description = "Nombre del tratamiento")
            String name
    ) {
        deleteTreatmentByNameUseCase.deleteTreatmentByName(name);
    }


}

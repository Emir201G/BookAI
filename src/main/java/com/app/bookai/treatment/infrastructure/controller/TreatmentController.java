package com.app.bookai.treatment.infrastructure.controller;

import com.app.bookai.treatment.application.dto.CreateTreatmentDTO;
import com.app.bookai.treatment.application.dto.TreatmentResponseDTO;
import com.app.bookai.treatment.application.mapper.TreatmentMapper;
import com.app.bookai.treatment.domain.model.Treatment;
import com.app.bookai.treatment.domain.port.in.CreateTreatmentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/treatment")
@RequiredArgsConstructor
public class TreatmentController {

    private final CreateTreatmentUseCase createTreatmentUseCase;
    private final TreatmentMapper treatmentMapper;

    @PostMapping("/create")
    public ResponseEntity<TreatmentResponseDTO> createTreatment(
            @RequestBody CreateTreatmentDTO requestDTO
    ) {

        Treatment treatment = treatmentMapper.toDomainTreatment(requestDTO);

        Treatment saved = createTreatmentUseCase.createTreatment(treatment);

        return ResponseEntity.ok(
                treatmentMapper.toTreatmentResponseDTO(saved)
        );
    }
}

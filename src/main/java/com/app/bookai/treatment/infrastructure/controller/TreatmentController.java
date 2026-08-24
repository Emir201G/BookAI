package com.app.bookai.treatment.infrastructure.controller;

import com.app.bookai.treatment.application.dto.CreateTreatmentDTO;
import com.app.bookai.treatment.application.dto.TreatmentResponseDTO;
import com.app.bookai.treatment.application.mapper.TreatmentMapper;
import com.app.bookai.treatment.domain.model.Treatment;
import com.app.bookai.treatment.domain.port.in.CreateTreatmentUseCase;
import com.app.bookai.treatment.domain.port.in.DeleteTreatmentByNameUseCase;
import com.app.bookai.treatment.domain.port.in.GetAllTreatmentUseCase;
import com.app.bookai.treatment.domain.port.in.GetTreatmentByNameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/treatment")
@RequiredArgsConstructor
public class TreatmentController {

    private final CreateTreatmentUseCase createTreatmentUseCase;
    private final GetAllTreatmentUseCase getAllTreatmentUseCase;
    private final GetTreatmentByNameUseCase getTreatmentByNameUseCase;
    private final DeleteTreatmentByNameUseCase deleteTreatmentByNameUseCase;
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

    @GetMapping("/all")
    public ResponseEntity<List<TreatmentResponseDTO>> getAllTreatment() {
        List<Treatment> treatments = getAllTreatmentUseCase.getAllTreatment();

        return ResponseEntity.ok(treatmentMapper.toTreatmentResponseDTO(treatments));
    }

    @GetMapping("/get-treatment/{name}")
    public ResponseEntity<TreatmentResponseDTO> getTreatmentByName(@PathVariable String name) {

        Treatment treatment = getTreatmentByNameUseCase.getTreatmentByName(name);
        return ResponseEntity.ok(treatmentMapper.toTreatmentResponseDTO(treatment));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteTreatment(@PathVariable String name) {
        deleteTreatmentByNameUseCase.deleteTreatmentByName(name);
        return ResponseEntity.ok("delete");
    }
}

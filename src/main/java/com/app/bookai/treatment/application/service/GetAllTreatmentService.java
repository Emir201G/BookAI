package com.app.bookai.treatment.application.service;

import com.app.bookai.treatment.domain.model.Treatment;
import com.app.bookai.treatment.domain.port.in.GetAllTreatmentUseCase;
import com.app.bookai.treatment.domain.port.out.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GetAllTreatmentService implements GetAllTreatmentUseCase {

    private final TreatmentRepository treatmentRepository;
    @Override
    public List<Treatment> getAllTreatment() {
        return treatmentRepository.findAll();
    }
}

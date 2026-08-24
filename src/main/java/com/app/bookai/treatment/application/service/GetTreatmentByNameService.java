package com.app.bookai.treatment.application.service;

import com.app.bookai.treatment.domain.exception.NotFoundByNameTreatmentException;
import com.app.bookai.treatment.domain.model.Treatment;
import com.app.bookai.treatment.domain.port.in.GetTreatmentByNameUseCase;
import com.app.bookai.treatment.domain.port.out.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTreatmentByNameService implements GetTreatmentByNameUseCase {
    private final TreatmentRepository treatmentRepository;

    @Override
    public Treatment getTreatmentByName(String name) {

        return treatmentRepository.findByName(name)
                .orElseThrow(() -> new NotFoundByNameTreatmentException(name));
    }
}

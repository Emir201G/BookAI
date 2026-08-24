package com.app.bookai.treatment.application.service;

import com.app.bookai.treatment.domain.exception.NameTreatmentAlreadyExistsException;
import com.app.bookai.treatment.domain.model.Treatment;
import com.app.bookai.treatment.domain.port.in.CreateTreatmentUseCase;
import com.app.bookai.treatment.domain.port.out.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTreatmentService implements CreateTreatmentUseCase {

    private final TreatmentRepository treatmentRepository;
    @Override
    public Treatment createTreatment(Treatment treatment) {

        if(treatmentRepository.existsByName(treatment.getName())) {
            throw new NameTreatmentAlreadyExistsException(treatment.getName());
        }

        return treatmentRepository.save(treatment);
    }
}

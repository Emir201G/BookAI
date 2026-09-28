package com.app.bookai.treatment.application.service;

import com.app.bookai.treatment.domain.exception.NotFoundByNameTreatmentException;
import com.app.bookai.treatment.domain.model.Treatment;
import com.app.bookai.treatment.domain.port.in.UpdateActiveUseCase;
import com.app.bookai.treatment.domain.port.out.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateActiveService implements UpdateActiveUseCase {

    private final TreatmentRepository treatmentRepository;

    @Override
    public void updateActiveUseCase(String name) {

        Treatment treatment = treatmentRepository.findByName(name)
                .orElseThrow(() ->
                        new NotFoundByNameTreatmentException(name));


        treatment.updateIsActive(false);

        treatmentRepository.save(treatment);
    }
}

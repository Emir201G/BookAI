package com.app.bookai.treatment.application.service;

import com.app.bookai.treatment.domain.exception.NotFoundByNameTreatmentException;
import com.app.bookai.treatment.domain.port.in.DeleteTreatmentByNameUseCase;
import com.app.bookai.treatment.domain.port.out.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteTreatmentByNameService implements DeleteTreatmentByNameUseCase {
    private final TreatmentRepository treatmentRepository;

    @Override
    @Transactional
    public void deleteTreatmentByName(String name) {

        if (!treatmentRepository.existsByName(name)) {
            throw new NotFoundByNameTreatmentException(name);
        }
        treatmentRepository.remove(name);
    }
}

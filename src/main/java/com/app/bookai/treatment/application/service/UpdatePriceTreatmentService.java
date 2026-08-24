package com.app.bookai.treatment.application.service;

import com.app.bookai.treatment.domain.exception.NotFoundByNameTreatmentException;
import com.app.bookai.treatment.domain.model.Treatment;
import com.app.bookai.treatment.domain.port.in.UpdatePriceTreatmentUseCase;
import com.app.bookai.treatment.domain.port.out.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UpdatePriceTreatmentService implements UpdatePriceTreatmentUseCase {
    private final TreatmentRepository treatmentRepository;

    @Override
    @Transactional
    public Treatment updatePriceTreatment(String name, BigDecimal price) {

        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Price must be greater than zero");
        }

        Treatment treatment = treatmentRepository.findByName(name)
                .orElseThrow(() -> new NotFoundByNameTreatmentException(name));
        treatment.updatePrice(price);
        return treatmentRepository.save(treatment);
    }
}

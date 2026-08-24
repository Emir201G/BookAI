package com.app.bookai.treatment.domain.port.in;

import com.app.bookai.treatment.domain.model.Treatment;

import java.math.BigDecimal;

public interface UpdatePriceTreatmentUseCase {
    Treatment updatePriceTreatment(String name, BigDecimal price);
}

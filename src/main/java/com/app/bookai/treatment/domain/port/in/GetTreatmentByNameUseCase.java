package com.app.bookai.treatment.domain.port.in;

import com.app.bookai.treatment.domain.model.Treatment;

public interface GetTreatmentByNameUseCase {
    Treatment getTreatmentByName(String name);
}

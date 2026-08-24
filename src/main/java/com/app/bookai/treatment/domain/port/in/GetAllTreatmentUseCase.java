package com.app.bookai.treatment.domain.port.in;

import com.app.bookai.treatment.domain.model.Treatment;

import java.util.List;

public interface GetAllTreatmentUseCase {
    List<Treatment> getAllTreatment();
}

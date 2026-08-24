package com.app.bookai.treatment.domain.port.out;

import com.app.bookai.treatment.domain.model.Treatment;

import java.util.List;
import java.util.Optional;

public interface TreatmentRepository {
    Treatment save(Treatment treatment);
    void remove(String name);
    Optional<Treatment> findByName(String name);
    boolean existsByName(String name);
    List<Treatment> findAll();
}

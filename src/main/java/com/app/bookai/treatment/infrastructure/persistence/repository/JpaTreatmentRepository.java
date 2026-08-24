package com.app.bookai.treatment.infrastructure.persistence.repository;

import com.app.bookai.treatment.domain.model.Treatment;
import com.app.bookai.treatment.infrastructure.persistence.entity.TreatmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTreatmentRepository extends JpaRepository<TreatmentEntity,Long> {

    Optional<TreatmentEntity> findByName(String name);
    boolean existsByName(String name);
    void deleteByName(String name);

}

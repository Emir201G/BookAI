package com.app.bookai.treatment.infrastructure.persistence.adapter;

import com.app.bookai.treatment.domain.exception.NotFoundByNameTreatmentException;
import com.app.bookai.treatment.domain.model.Treatment;
import com.app.bookai.treatment.domain.port.out.TreatmentRepository;
import com.app.bookai.treatment.infrastructure.persistence.entity.TreatmentEntity;
import com.app.bookai.treatment.infrastructure.persistence.mapper.TreatmentPersistenceMapper;
import com.app.bookai.treatment.infrastructure.persistence.repository.JpaTreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TreatmentPersistenceAdapter implements TreatmentRepository {

    private final JpaTreatmentRepository treatmentRepository;
    private final TreatmentPersistenceMapper treatmentPersistenceMapper;

    @Override
    public Treatment save(Treatment treatment) {
        TreatmentEntity entity = treatmentPersistenceMapper.toEntity(treatment);
        TreatmentEntity save = treatmentRepository.save(entity);
        return treatmentPersistenceMapper.toDomain(save);
    }

    @Override
    public void remove(String name) {

    }

    @Override
    public Optional<Treatment> findByName(String name) {
        TreatmentEntity entity = treatmentRepository.findByName(name)
                .orElseThrow(() -> new NotFoundByNameTreatmentException(name));

        Treatment treatment = treatmentPersistenceMapper.toDomain(entity);
        return Optional.of(treatment);
    }

    @Override
    public boolean existsByName(String name) {

        return treatmentRepository.existsByName(name);
    }

    @Override
    public List<Treatment> findAll() {
        List<TreatmentEntity> treatments = treatmentRepository.findAll();

        return treatmentPersistenceMapper.toDomain(treatments);
    }
}

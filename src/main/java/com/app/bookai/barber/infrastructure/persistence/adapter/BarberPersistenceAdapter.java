package com.app.bookai.barber.infrastructure.persistence.adapter;

import com.app.bookai.barber.domain.exception.NotFoundByNameException;
import com.app.bookai.shared.exception.NotFoundByPhoneNumber;
import com.app.bookai.barber.domain.model.Barber;
import com.app.bookai.barber.domain.model.WorkingHour;
import com.app.bookai.barber.domain.port.out.BarberRepository;
import com.app.bookai.barber.infrastructure.persistence.entity.BarberEntity;
import com.app.bookai.barber.infrastructure.persistence.entity.WorkingHourEntity;
import com.app.bookai.barber.infrastructure.persistence.mapper.BarberPersistenceMapper;
import com.app.bookai.barber.infrastructure.persistence.repository.JpaBarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BarberPersistenceAdapter implements BarberRepository {
    private final JpaBarberRepository jpaBarberRepository;
    private final BarberPersistenceMapper barberPersistenceMapper;

    @Override
    @Transactional
    public Barber save(Barber barber) {

        BarberEntity entity = barberPersistenceMapper.toBarberEntity(barber);
        if (entity.getDayOffs() != null) {
            entity.getDayOffs().forEach(dayOff -> dayOff.setBarber(entity));
        }
        if (entity.getWorkingHours() != null) {
            entity.getWorkingHours().forEach(workingHour -> workingHour.setBarber(entity));
        }
        if (entity.getWorkingHourOverrides() != null) {
            entity.getWorkingHourOverrides().forEach(workingHourOverride -> workingHourOverride.setBarber(entity));
        }
        BarberEntity saved = jpaBarberRepository.save(entity);

        return barberPersistenceMapper.toDomain(saved);
    }

    @Override
    public void remove(Barber barber) {
        BarberEntity b = jpaBarberRepository.findByPhoneNumber(barber.getPhoneNumber()).orElseThrow(() -> new NotFoundByPhoneNumber(barber.getPhoneNumber()));
        jpaBarberRepository.delete(b);
    }


    @Override
    public List<Barber> getAll() {
        List<BarberEntity> entities = jpaBarberRepository.findAll();
        return barberPersistenceMapper.toDomain(entities);
    }


    @Transactional
    @Override
    public Barber update(Barber barber) {
        BarberEntity barberEntity = jpaBarberRepository.findByPhoneNumber(barber.getPhoneNumber()).orElseThrow(() -> new NotFoundByPhoneNumber(barber.getPhoneNumber()));
        barberEntity.setName(barber.getName());
        barberEntity.setPhoneNumber(barber.getPhoneNumber());
        jpaBarberRepository.save(barberEntity);
        return barberPersistenceMapper.toDomain(barberEntity);
    }

    @Transactional
    @Override
    public void updateWorkingHours(String phoneNumber, List<WorkingHour> workingHours) {

        BarberEntity entity = jpaBarberRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new NotFoundByPhoneNumber(phoneNumber));

        System.out.println("ANTES: " + entity.getWorkingHours().size());

        entity.getWorkingHours().clear();

        if (workingHours != null && !workingHours.isEmpty()) {

            List<WorkingHourEntity> workingHourEntities = barberPersistenceMapper.toWorkingHourEntity(workingHours);

            System.out.println("MAPPER ENTITY: " + workingHourEntities.size());

            workingHourEntities.forEach(workingHourEntity -> {

                workingHourEntity.setId(null);
                workingHourEntity.setBarber(entity);

                entity.getWorkingHours().add(workingHourEntity);
            });
        }

        System.out.println("DESPUES: " + entity.getWorkingHours().size());

        jpaBarberRepository.save(entity);
    }

    @Override
    public Optional<Barber> findByPhoneNumber(String phoneNumber) {
        BarberEntity barberEntity = jpaBarberRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new NotFoundByPhoneNumber(phoneNumber));
        Barber barber = barberPersistenceMapper.toDomain(barberEntity);
        return Optional.of(barber);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return jpaBarberRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<Barber> findByName(String name) {

        BarberEntity barberEntity = jpaBarberRepository.findByName(name).orElseThrow(() -> new NotFoundByNameException(name));

        Barber barber = barberPersistenceMapper.toDomain(barberEntity);
        return Optional.of(barber);
    }

    @Override
    public boolean existsByName(String name) {
        return jpaBarberRepository.existsByName(name);
    }


}

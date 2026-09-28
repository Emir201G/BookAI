package com.app.bookai.barber.infrastructure.persistence.adapter;

import com.app.bookai.barber.domain.exception.NotFoundByNameException;
import com.app.bookai.barber.domain.model.DayOff;
import com.app.bookai.barber.domain.model.WorkingHourOverride;
import com.app.bookai.barber.infrastructure.persistence.entity.DayOffEntity;
import com.app.bookai.barber.infrastructure.persistence.entity.WorkingHourOverrideEntity;
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

        entity.getWorkingHours().clear();

        if (workingHours != null && !workingHours.isEmpty()) {

            List<WorkingHourEntity> workingHourEntities = barberPersistenceMapper.toWorkingHourEntity(workingHours);

            workingHourEntities.forEach(workingHourEntity -> {

                workingHourEntity.setId(null);
                workingHourEntity.setBarber(entity);

                entity.getWorkingHours().add(workingHourEntity);
            });
        }
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

    @Transactional
    @Override
    public Barber addWorkingHour(String name, WorkingHour workingHour) {

        BarberEntity barberEntity = jpaBarberRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundByNameException(name));

        WorkingHourEntity workingHourEntity =
                barberPersistenceMapper.toWorkingHourEntity(workingHour);

        workingHourEntity.setId(null);
        workingHourEntity.setBarber(barberEntity);

        barberEntity.getWorkingHours().add(workingHourEntity);

        return barberPersistenceMapper.toDomain(barberEntity);
    }

    @Transactional
    @Override
    public Barber addDayOff(String name, DayOff dayOff) {

        BarberEntity barberEntity = jpaBarberRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundByNameException(name));

        DayOffEntity dayOffEntity =
                barberPersistenceMapper.toDayOffEntity(dayOff);
        dayOffEntity.setId(null);
        dayOffEntity.setBarber(barberEntity);

        barberEntity.getDayOffs().add(dayOffEntity);

        return barberPersistenceMapper.toDomain(barberEntity);
    }

    @Transactional
    @Override
    public Barber addWorkingHourOverride(String name, WorkingHourOverride workingHourOverride) {

        BarberEntity barberEntity = jpaBarberRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundByNameException(name));

        WorkingHourOverrideEntity workingHourOverrideEntity =
                barberPersistenceMapper.toWorkingHourOverrideEntity(workingHourOverride);

        workingHourOverrideEntity.setId(null);
        workingHourOverrideEntity.setBarber(barberEntity);

        barberEntity.getWorkingHourOverrides().add(workingHourOverrideEntity);
        return barberPersistenceMapper.toDomain(barberEntity);
    }

    @Override
    public List<WorkingHour> getWorkingHours(String name) {

        BarberEntity entity = jpaBarberRepository.
                findByName(name)
                .orElseThrow(
                        () -> new NotFoundByNameException(name));

        List<WorkingHourEntity> workingHourEntities = jpaBarberRepository
                .findWorkingHoursByBarberName(entity.getName());

        return barberPersistenceMapper.
                toWorkingHour(workingHourEntities);
    }

    @Override
    public List<DayOff> getDayOffs(String name) {

        BarberEntity entity = jpaBarberRepository
                .findByName(name)
                .orElseThrow(
                        () -> new NotFoundByNameException(name)
                );
        List<DayOffEntity> dayOffEntities = jpaBarberRepository
                .findDayOffsByBarberName(entity.getName());

        return barberPersistenceMapper
                .toDayOff(dayOffEntities);
    }

    @Override
    public List<WorkingHourOverride> getWorkingHourOverride(String name) {

        BarberEntity entity = jpaBarberRepository
                .findByName(name)
                .orElseThrow(
                        () -> new NotFoundByNameException(name)
                );

        List<WorkingHourOverrideEntity> overrides = jpaBarberRepository
                .findWorkingHourOverridesByBarberName(name);

        return barberPersistenceMapper
                .toWorkingHourOverride(overrides);
    }

}

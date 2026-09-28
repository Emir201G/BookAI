package com.app.bookai.appointment.infrastructure.persistence.adapter;

import com.app.bookai.appointment.domain.enums.AppointmentStatus;
import com.app.bookai.appointment.domain.model.Appointment;
import com.app.bookai.appointment.domain.port.out.AppointmentRepository;
import com.app.bookai.appointment.infrastructure.persistence.entity.AppointmentEntity;
import com.app.bookai.appointment.infrastructure.persistence.mapper.AppointmentPersistenceMapper;
import com.app.bookai.appointment.infrastructure.persistence.repository.JpaAppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AppointmentPersistenceAdapter implements AppointmentRepository {
    private final AppointmentPersistenceMapper appointmentPersistenceMapper;
    private final JpaAppointmentRepository jpaAppointmentRepository;

    @Override
    @Transactional
    public Appointment save(Appointment appointment) {

        AppointmentEntity appointmentEntity = appointmentPersistenceMapper.toEntity(appointment);

        if (appointmentEntity.getTreatments() != null) {
            appointmentEntity.getTreatments().forEach(
                    treatment -> treatment.setAppointment(appointmentEntity));
        }

        AppointmentEntity saved = jpaAppointmentRepository.save(appointmentEntity);

        return appointmentPersistenceMapper.toDomain(saved);
    }

    @Override
    public boolean existsOverlappingAppointment(Long barberId,
                                                LocalDate date,
                                                LocalTime startTime,
                                                LocalTime endTime) {
        return jpaAppointmentRepository.existsOverlappingAppointment(
                barberId, date, startTime, endTime
        );
    }

    @Override
    public Optional<Appointment> findByCustomerIdAndDateAndStartTime(Long customerId, LocalDate date, LocalTime startTime) {
        return jpaAppointmentRepository
                .findByCustomerIdAndDateAndStartTime(
                        customerId,
                        date,
                        startTime
                )
                .map(appointmentPersistenceMapper::toDomain);
    }

    @Override
    public List<Appointment> getAllAppointment() {
        List<AppointmentEntity> entities = jpaAppointmentRepository.findAll();

        return appointmentPersistenceMapper.toDomainList(entities);
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        AppointmentEntity entity = jpaAppointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        return Optional.of(appointmentPersistenceMapper.toDomain(entity));
    }

}

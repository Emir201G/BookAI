package com.app.bookai.appointment.application.service;

import com.app.bookai.barber.domain.model.WorkingHour;

import java.time.DayOfWeek;
import java.util.List;

import com.app.bookai.appointment.domain.port.out.AppointmentRepository;
import com.app.bookai.appointment.domain.port.in.CheckAvailabilityUseCase;
import com.app.bookai.barber.domain.model.WorkingHourOverride;
import com.app.bookai.barber.domain.port.out.DayOffRepository;
import com.app.bookai.barber.domain.port.out.WorkingHourOverrideRepository;
import com.app.bookai.barber.domain.port.out.WorkingHourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckAvailabilityService implements CheckAvailabilityUseCase {

    private final DayOffRepository dayOffRepository;
    private final WorkingHourRepository workingHourRepository;
    private final WorkingHourOverrideRepository workingHourOverrideRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public boolean isAvailable(
            Long barberId,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime
    ) {

        // 1. DAY OFF
        if (dayOffRepository.existsByBarberIdAndDate(barberId, date)) {
            return false;
        }

        // 2. WORKING HOUR OVERRIDE
        Optional<WorkingHourOverride> override =
                workingHourOverrideRepository
                        .findByBarberIdAndDate(barberId, date);

        if (override.isPresent()) {

            WorkingHourOverride workingHourOverride = override.get();

            if (!isWithinWorkingHours(
                    startTime,
                    endTime,
                    workingHourOverride.getStartTime(),
                    workingHourOverride.getEndTime()
            )) {
                return false;
            }

        } else {

            // 3. WORKING HOUR NORMAL
            DayOfWeek dayOfWeek = date.getDayOfWeek();

            List<WorkingHour> workingHours =
                    workingHourRepository.findByBarberIdAndDayOfWeek(
                            barberId,
                            dayOfWeek
                    );

            boolean withinWorkingHours = workingHours.stream()
                    .anyMatch(workingHour ->
                            isWithinWorkingHours(
                                    startTime,
                                    endTime,
                                    workingHour.getStartTime(),
                                    workingHour.getEndTime()
                            )
                    );

            if (!withinWorkingHours) {
                return false;
            }
        }

        // 4. APPOINTMENT
        if (appointmentRepository.existsOverlappingAppointment(
                barberId,
                date,
                startTime,
                endTime
        )) {
            return false;
        }

        return true;
    }


    private boolean isWithinWorkingHours(
            LocalTime requestedStart,
            LocalTime requestedEnd,
            LocalTime workingStart,
            LocalTime workingEnd
    ) {
        return !requestedStart.isBefore(workingStart)
                && !requestedEnd.isAfter(workingEnd);
    }
}

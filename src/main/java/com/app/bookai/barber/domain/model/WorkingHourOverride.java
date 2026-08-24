package com.app.bookai.barber.domain.model;

import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkingHourOverride {
    private Long id;
    private String reason;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}

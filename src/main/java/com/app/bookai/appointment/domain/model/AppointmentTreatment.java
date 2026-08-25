package com.app.bookai.appointment.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentTreatment {
    private Long id;

    private Long appointmentId;

    private Long treatmentId;

    private BigDecimal price;

    private Integer durationMinutes;
}

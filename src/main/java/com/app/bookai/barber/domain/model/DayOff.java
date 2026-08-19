package com.app.bookai.barber.domain.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DayOff {
    private Long id;
    private LocalDate date;
    private String reason;

}

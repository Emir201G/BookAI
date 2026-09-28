package com.app.bookai.barber.domain.port.out;

import java.time.LocalDate;

public interface DayOffRepository {

    boolean existsByBarberIdAndDate(
            Long barberId,
            LocalDate date
    );}

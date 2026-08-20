package com.app.bookai.barber.domain.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NotFundByDate extends RuntimeException {
    public NotFundByDate(LocalDate date) {
        super("Not day off fund for date: " + date);
    }
}

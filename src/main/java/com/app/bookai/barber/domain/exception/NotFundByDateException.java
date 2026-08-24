package com.app.bookai.barber.domain.exception;

import java.time.LocalDate;

public class NotFundByDateException extends RuntimeException {
    public NotFundByDateException(LocalDate date) {
        super("Not day off fund for date: " + date);
    }
}

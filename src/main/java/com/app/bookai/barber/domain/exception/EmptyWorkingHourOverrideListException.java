package com.app.bookai.barber.domain.exception;

public class EmptyWorkingHourOverrideListException extends RuntimeException {
    public EmptyWorkingHourOverrideListException() {
        super("The working hour override list cannot be empty");

    }
}

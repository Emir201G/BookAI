package com.app.bookai.barber.domain.exception;

public class EmptyWorkingHourListException extends RuntimeException {
    public EmptyWorkingHourListException() {
        super("The working hour list cannot be empty");

    }
}

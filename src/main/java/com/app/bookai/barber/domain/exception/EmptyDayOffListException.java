package com.app.bookai.barber.domain.exception;

public class EmptyDayOffListException extends RuntimeException {
    public EmptyDayOffListException( ) {
        super("The day off list cannot be empty");

    }
}

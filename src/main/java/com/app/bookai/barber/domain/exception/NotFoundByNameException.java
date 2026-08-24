package com.app.bookai.barber.domain.exception;

public class NotFoundByNameException extends RuntimeException {
    public NotFoundByNameException(String name) {
        super("Not found by name " + name);
    }
}

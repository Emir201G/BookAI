package com.app.bookai.barber.domain.exception;

public class NameAlreadyExistsException extends RuntimeException {
    public NameAlreadyExistsException(String name) {
        super("Name " + name + " already exists");
    }
}

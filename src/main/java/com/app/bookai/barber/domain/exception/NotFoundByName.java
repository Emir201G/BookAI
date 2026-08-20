package com.app.bookai.barber.domain.exception;

public class NotFoundByName extends RuntimeException {
    public NotFoundByName(String name) {
        super("Not found by name " + name);
    }
}

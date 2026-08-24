package com.app.bookai.treatment.domain.exception;

public class NotFoundByNameTreatmentException extends RuntimeException {
    public NotFoundByNameTreatmentException(String name) {
        super("Treatment " + name + " not found");
    }
}

package com.app.bookai.treatment.domain.exception;

public class NameTreatmentAlreadyExistsException extends RuntimeException {
    public NameTreatmentAlreadyExistsException(String name) {
        super("Treatment with name " + name + " already exists");
    }
}

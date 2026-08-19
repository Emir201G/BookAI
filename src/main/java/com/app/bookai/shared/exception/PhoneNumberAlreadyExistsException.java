package com.app.bookai.shared.exception;

public class PhoneNumberAlreadyExistsException extends RuntimeException {
    public PhoneNumberAlreadyExistsException(String numberPhone) {
        super("Phone number " + numberPhone + " already exists");
    }
}

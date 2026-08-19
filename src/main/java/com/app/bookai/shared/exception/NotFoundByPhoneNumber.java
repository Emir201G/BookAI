package com.app.bookai.shared.exception;

public class NotFoundByPhoneNumber extends RuntimeException {
    public NotFoundByPhoneNumber(String phoneNumber) {
        super("Not found by phone number " + phoneNumber);
    }
}

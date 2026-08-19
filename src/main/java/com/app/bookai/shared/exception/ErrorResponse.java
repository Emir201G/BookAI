package com.app.bookai.shared.exception;

public record ErrorResponse(
        String message,
        int status,
        String timestamp,
        String phat) {
}

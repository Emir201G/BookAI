package com.app.bookai.shared.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@RestControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ErrorResponse> buildErrorResponse(
            Exception ex, HttpStatus httpStatus, HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                httpStatus.value(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                request.getRequestURI()
        );

        return new ResponseEntity<>(errorResponse, httpStatus);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(NotFoundByPhoneNumber.class)
    public ResponseEntity<ErrorResponse> handleNotFoundByPhoneNumber(
            NotFoundByPhoneNumber notFoundByPhoneNumber,
            HttpServletRequest request) {
        return buildErrorResponse(notFoundByPhoneNumber, HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler(PhoneNumberAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlePhoneNumberAlreadyExistsException(
            PhoneNumberAlreadyExistsException phoneNumberAlreadyExistsException,
            HttpServletRequest request
    ){
        return buildErrorResponse(phoneNumberAlreadyExistsException, HttpStatus.BAD_REQUEST, request);
    }
}

package com.app.bookai.shared.exception;

import com.app.bookai.barber.domain.exception.EmptyDayOffListException;
import com.app.bookai.barber.domain.exception.NotFoundByName;
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

    @ExceptionHandler(NotFoundByName.class)
    public ResponseEntity<ErrorResponse> handleNotFoundByName(
            NotFoundByName notFoundByName,
            HttpServletRequest request
    ){
        return buildErrorResponse(notFoundByName, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EmptyDayOffListException.class)
    public ResponseEntity<ErrorResponse> handleEmptyDayOffListException(
            EmptyDayOffListException emptyDayOffListException,
            HttpServletRequest request
    ){
        return buildErrorResponse(emptyDayOffListException, HttpStatus.BAD_REQUEST, request);
    }
}

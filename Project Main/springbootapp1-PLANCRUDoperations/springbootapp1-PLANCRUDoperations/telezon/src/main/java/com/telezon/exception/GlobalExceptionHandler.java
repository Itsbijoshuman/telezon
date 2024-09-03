package com.telezon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PrepaidPlanNotFoundException.class)
    public ResponseEntity<String> handlePrepaidPlanNotFoundException(PrepaidPlanNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicatePrepaidPlanException.class)
    public ResponseEntity<String> handleDuplicatePrepaidPlanException(DuplicatePrepaidPlanException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Other exception handlers...
}

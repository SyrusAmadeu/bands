package com.dws.bands.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<String> handleExternalError(ExternalServiceException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationError(ConstraintViolationException ex) {
    	return ResponseEntity
    			.status(HttpStatus.BAD_REQUEST)
    			.body(ex.getMessage());
    }
    
    @ExceptionHandler(BandNotFoundException.class)
    public ResponseEntity<String> handleBandNotFoundError(BandNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
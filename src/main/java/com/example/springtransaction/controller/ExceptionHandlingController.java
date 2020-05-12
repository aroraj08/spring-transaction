package com.example.springtransaction.controller;

import com.example.springtransaction.model.ErrorDto;
import com.example.springtransaction.exceptions.CustomerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class ExceptionHandlingController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);

    @ResponseStatus
    @ExceptionHandler(value= CustomerNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCustomerNotFoundException(CustomerNotFoundException ex) {

        logger.error("Exception: CUSTOMER_NOT_FOUND " + ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDto.builder()
                .status(HttpStatus.NOT_FOUND)
                .errorCode("CUSTOMER_NOT_FOUND")
                .errorMessage(ex.getMessage())
                .time(OffsetDateTime.now())
                .build());
    }

    @ExceptionHandler(value= DataAccessException.class)
    public ResponseEntity<ErrorDto> handleDataAccessException(DataAccessException ex) {

        logger.error("Exception: DATA_ACCESS_EXCEPTION " + ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorDto.builder()
                 .status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .errorCode("DATA_ACCESS_EXCEPTION")
                 .errorMessage("Exception while accessing Database")
                 .time(OffsetDateTime.now())
                 .build());
    }
}

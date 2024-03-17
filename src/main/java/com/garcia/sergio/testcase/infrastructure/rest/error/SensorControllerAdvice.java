package com.garcia.sergio.testcase.infrastructure.rest.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeParseException;

@Slf4j
@ControllerAdvice(annotations= RestController.class)
public class SensorControllerAdvice {

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> errorHandler(Exception e) {
        log.error("*** ERROR: {}", e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST )
                .body("ERROR: " + e.getMessage());
    }
}

package com.garcia.sergio.testcase.infrastructure.rest.error;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.format.DateTimeParseException;

class SensorControllerAdviceTest {

    @Test
    void testDateFormatErrorHandler() {
        SensorControllerAdvice sensorControllerAdvice = new SensorControllerAdvice();
        ResponseEntity<String> response = sensorControllerAdvice.errorHandler(new DateTimeParseException("Parse error", "seq", 17));

        assertEquals("ERROR: Parse error", response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
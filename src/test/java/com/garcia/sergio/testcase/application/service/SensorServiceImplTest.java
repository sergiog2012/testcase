package com.garcia.sergio.testcase.application.service;

import com.garcia.sergio.testcase.application.SensorRepository;
import com.garcia.sergio.testcase.domain.Sensor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.when;

class SensorServiceImplTest {
    @Mock
    SensorRepository sensorRepository;
    @InjectMocks
    SensorServiceImpl sensorServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPersistSensor() {
        Sensor sensor = new Sensor();
        sensor.setSensorId("SensorTest");
        sensor.setType("testing");
        sensor.setValue(1.1);
        sensor.setTimestamp(Instant.now());

        Sensor sensorResponse = new Sensor();
        sensorResponse.setSensorId("SensorTest");
        sensorResponse.setType("testing");
        sensorResponse.setValue(1.1);
        sensorResponse.setTimestamp(Instant.now());
        sensorResponse.setId("ID");

        when(sensorRepository.save(sensor)).thenReturn(sensorResponse);

        Sensor result = sensorServiceImpl.persistSensor(sensor);
        Assertions.assertEquals(sensorResponse, result);
    }

    @Test
    void testRetrieveAllSensors() {
        Sensor sensorResponse = new Sensor();
        sensorResponse.setSensorId("SensorTest");
        sensorResponse.setType("testing");
        sensorResponse.setValue(1.1);
        sensorResponse.setTimestamp(Instant.now());
        sensorResponse.setId("ID");

        when(sensorRepository.findAll()).thenReturn(List.of(sensorResponse));

        List<Sensor> result = sensorServiceImpl.retrieveAllSensors();
        Assertions.assertEquals(List.of(sensorResponse), result);
    }
}
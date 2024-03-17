package com.garcia.sergio.testcase.infrastructure.mongo;

import com.garcia.sergio.testcase.domain.Sensor;
import com.garcia.sergio.testcase.infrastructure.mongo.dto.MongoSensor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class MongoSensorRepositoryTest {
    @Mock
    MongoTemplate mongoTemplate;
    @Mock
    SensorMapper sensorMapper;
    @InjectMocks
    MongoSensorRepository mongoSensorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Sensor sensorResponse = new Sensor();
        sensorResponse.setSensorId("SensorTest");
        sensorResponse.setType("testing");
        sensorResponse.setValue(1.1);
        sensorResponse.setTimestamp(Instant.now());
        sensorResponse.setId("ID");

        when(mongoTemplate.findAll(any())).thenReturn(List.of(new MongoSensor()));
        when(sensorMapper.toDomain(any())).thenReturn(sensorResponse);

        List<Sensor> result = mongoSensorRepository.findAll();
        Assertions.assertEquals(List.of(sensorResponse), result);
    }

    @Test
    void testSave() {
        Sensor sensorResponse = new Sensor();
        sensorResponse.setSensorId("SensorTest");
        sensorResponse.setType("testing");
        sensorResponse.setValue(1.1);
        sensorResponse.setTimestamp(Instant.now());
        sensorResponse.setId("ID");

        when(mongoTemplate.save(any())).thenReturn(new MongoSensor());
        when(sensorMapper.toDomain(any())).thenReturn(sensorResponse);
        when(sensorMapper.toMongo(any())).thenReturn(new MongoSensor());

        Sensor result = mongoSensorRepository.save(sensorResponse);
        Assertions.assertEquals(sensorResponse, result);
    }
}
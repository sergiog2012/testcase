package com.garcia.sergio.testcase.application.service;

import com.garcia.sergio.testcase.application.SensorRepository;
import com.garcia.sergio.testcase.domain.Sensor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService{

    private final SensorRepository sensorRepository;

    public Sensor persistSensor(Sensor sensor) {

        return sensorRepository.save(sensor);
    }

    public List<Sensor> retrieveAllSensors() {

        return sensorRepository.findAll();
    }

}

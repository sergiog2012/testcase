package com.garcia.sergio.testcase.application.service;

import com.garcia.sergio.testcase.domain.Sensor;

import java.util.List;

public interface SensorService {

    Sensor persistSensor(Sensor sensor);

    List<Sensor> retrieveAllSensors();
}

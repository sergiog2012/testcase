package com.garcia.sergio.testcase.application;


import com.garcia.sergio.testcase.domain.Sensor;

import java.util.List;

public interface SensorRepository {

    List<Sensor> findAll();

    Sensor save(Sensor sensor);
}

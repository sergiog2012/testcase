package com.garcia.sergio.testcase.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class Sensor {

    private String id;
    private String sensorId;
    private Instant timestamp;
    private String type;
    private Double value;
}

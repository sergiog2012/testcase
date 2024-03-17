package com.garcia.sergio.testcase.infrastructure.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SensorDto {

        private String id;
        private String sensorId;
        private String timestamp;
        private String type;
        private Double value;
}

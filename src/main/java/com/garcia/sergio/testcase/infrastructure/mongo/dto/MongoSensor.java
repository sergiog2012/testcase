package com.garcia.sergio.testcase.infrastructure.mongo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("sensor-events")
public class MongoSensor {

        @Id
        String id;
        String sensorId;
        String timestamp;
        String type;
        Double value;
}

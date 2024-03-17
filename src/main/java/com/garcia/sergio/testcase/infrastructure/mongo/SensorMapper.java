package com.garcia.sergio.testcase.infrastructure.mongo;

import com.garcia.sergio.testcase.domain.Sensor;
import com.garcia.sergio.testcase.infrastructure.mongo.dto.MongoSensor;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Instant;

@Mapper
public interface SensorMapper {

    @Mapping(target = "timestamp", ignore = true)
    @Mapping(source = "value", target = "value", numberFormat = "$#.0")
    Sensor toDomain(MongoSensor mongoSensor);

    MongoSensor toMongo(Sensor sensor);

    @AfterMapping
    default void mapTimestamp(@MappingTarget Sensor sensor, MongoSensor mongoSensor){
        Instant instant = Instant.parse(mongoSensor.getTimestamp());
        sensor.setTimestamp(instant);
    }
}

package com.garcia.sergio.testcase.infrastructure.rest;

import com.garcia.sergio.testcase.domain.Sensor;
import com.garcia.sergio.testcase.infrastructure.rest.dto.SensorDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Instant;

@Mapper
public interface SensorResponseMapper {

    SensorDto toDto(Sensor Sensor);

    @Mapping(target = "timestamp", ignore = true)
    @Mapping(source = "value", target = "value", numberFormat = "$#.0")
    Sensor toDomain(SensorDto sensorDto);

    @AfterMapping
    default void mapTimestamp(@MappingTarget Sensor sensor, SensorDto sensorDto) {
        Instant instant = Instant.parse(sensorDto.getTimestamp());
        sensor.setTimestamp(instant);

    }
}

package com.garcia.sergio.testcase.infrastructure.rest;

import com.garcia.sergio.testcase.application.service.SensorService;
import com.garcia.sergio.testcase.domain.Sensor;
import com.garcia.sergio.testcase.infrastructure.rest.dto.SensorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/sensor-events")
@RestController
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;
    private final SensorResponseMapper sensorResponseMapper;

    @Operation(summary = "Obtain a list of all sensor events from MongoDB",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operation is correct"),
                    @ApiResponse(responseCode = "500", description = "Operation is incorrect"),
                    @ApiResponse(responseCode = "400", description = "Invalid input")})
    @GetMapping
    private ResponseEntity<List<SensorDto>> getAllSensors() {
        List<Sensor> sensors = sensorService.retrieveAllSensors();
        List<SensorDto> response = sensors.stream()
                .map(sensorResponseMapper::toDto)
                .toList();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Persist a new sensor event in MongoDB",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Operation is correct"),
                    @ApiResponse(responseCode = "500", description = "Operation is incorrect"),
                    @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping
    private  ResponseEntity<SensorDto> postSensor(@RequestBody(required = true) SensorDto sensorDto) {
        Sensor sensor = sensorResponseMapper.toDomain(sensorDto);
        Sensor sensorPersisted = sensorService.persistSensor(sensor);

        return new ResponseEntity<>(sensorResponseMapper.toDto(sensorPersisted), HttpStatus.CREATED);
    }

}

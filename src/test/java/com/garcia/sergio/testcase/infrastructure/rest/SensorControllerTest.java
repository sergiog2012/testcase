package com.garcia.sergio.testcase.infrastructure.rest;

import com.garcia.sergio.testcase.TestcaseApplication;
import com.garcia.sergio.testcase.docker.MongoDBContainerSetup;
import com.garcia.sergio.testcase.infrastructure.mongo.MongoSensorRepository;
import com.garcia.sergio.testcase.infrastructure.rest.dto.SensorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(classes = TestcaseApplication.class)
class SensorControllerTest extends MongoDBContainerSetup {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    MongoSensorRepository repository;
    @Autowired
    SensorResponseMapper sensorResponseMapper;

    static ObjectMapper mapper = new ObjectMapper();

    @Test
    void ok_when_get_all_sensor_events() throws Exception {
        var events = repository.findAll().stream()
                .map(event -> sensorResponseMapper.toDto(event))
                .toList();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/sensor-events"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(events)));
    }

    @Test
    void ok_when_post_new_sensor_event() throws Exception {
        SensorDto sensorDto = new SensorDto();
        sensorDto.setSensorId("SensorTest");
        sensorDto.setType("testing");
        sensorDto.setValue(1.1);
        sensorDto.setTimestamp(Instant.now().toString());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/sensor-events")
                        .content(mapper.writeValueAsString(sensorDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void bad_request_when_timestamp_is_bad_format() throws Exception {
        SensorDto sensorDto = new SensorDto();
        sensorDto.setSensorId("SensorTest");
        sensorDto.setType("testing");
        sensorDto.setValue(1.1);
        sensorDto.setTimestamp("Induce error");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/sensor-events")
                        .content(mapper.writeValueAsString(sensorDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}

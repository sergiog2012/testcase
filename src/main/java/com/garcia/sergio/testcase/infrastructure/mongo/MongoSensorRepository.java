package com.garcia.sergio.testcase.infrastructure.mongo;


import com.garcia.sergio.testcase.application.SensorRepository;
import com.garcia.sergio.testcase.domain.Sensor;
import com.garcia.sergio.testcase.infrastructure.mongo.dto.MongoSensor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MongoSensorRepository implements SensorRepository {

    private  final MongoTemplate mongoTemplate;
    private  final SensorMapper sensorMapper;

    @Override
    public List<Sensor> findAll(){
        List<MongoSensor> sensors = mongoTemplate.find(new Query(Criteria.where("_id").exists(true)), MongoSensor.class);

        return sensors.stream()
                .map(sensorMapper::toDomain)
                .toList();
    }

    @Override
    public Sensor save(Sensor sensor) {
        MongoSensor mongoSensor = sensorMapper.toMongo(sensor);
        MongoSensor mongoSensorPersisted = mongoTemplate.save(mongoSensor);

        return sensorMapper.toDomain(mongoSensorPersisted);
    }

}

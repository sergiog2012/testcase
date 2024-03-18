db.createCollection('sensor-events');

db['sensor-events'].insertMany([
    {
        "sensorId": "sensor_1",
        "timestamp": "2024-02-15T12:34:56Z",
        "type": "temperature",
        "value": 21.6
    },
    {
        "sensorId": "sensor_2",
        "timestamp": "2024-02-15T12:34:56Z",
        "type": "temperature",
        "value": 25.3
    },
    {
        "sensorId": "sensor_3",
        "timestamp": "2024-02-15T12:34:56Z",
        "type": "temperature",
        "value": 20.1
    }]
);
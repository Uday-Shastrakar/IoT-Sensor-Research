package com.electronic.sensor.service

class SensorService {
    fun saveSensorData(sensorData: SensorData): SensorData {
        return repository.save(sensorData)
    }

    fun getAllSensorData(): List<SensorData> {
        return repository.findAll()
    }
}
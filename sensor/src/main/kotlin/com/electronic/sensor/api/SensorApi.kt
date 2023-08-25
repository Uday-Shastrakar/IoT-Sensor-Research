package com.electronic.sensor.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*
import kotlin.collections.HashMap

@RestController
@RequestMapping("/sensors")
class SensorApi {

    private val random: Random = Random()

    @GetMapping("/temperature")
    fun getTemperatureReading(
        @RequestParam("sensorId") sensorId: String
    ): ResponseEntity<Map<String, Any>>? {
        val temperature: Double = -20.0 + (40.0 - -20.0) * random.nextDouble()
        val sensorReading: MutableMap<String, Any> = HashMap()
        sensorReading["sensorId"] = sensorId
        sensorReading["type"] = "temperature"
        sensorReading["reading"] = temperature
        return ResponseEntity.ok(sensorReading)
    }

    @GetMapping("/humidity")
    fun getHumidityReading(
        @RequestParam("sensorId") sensorId: String
    ): ResponseEntity<Map<String, Any>>? {
        val humidity: Double = 0.0 + (100.0 - 0.0) * random.nextDouble()
        val sensorReading: MutableMap<String, Any> = HashMap()
        sensorReading["sensorId"] = sensorId
        sensorReading["type"] = "humidity"
        sensorReading["reading"] = humidity
        return ResponseEntity.ok(sensorReading)
    }

}
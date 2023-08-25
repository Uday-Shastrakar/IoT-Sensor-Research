package com.arduino.arduionservice.api

import com.arduino.arduionservice.service.ArduinoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.io.IOException
@Controller
class ArduinoApi (private var arduinoService: ArduinoService){

    @PostMapping("/send")
    fun sendData(@RequestBody data: String): ResponseEntity<String> {
        return try {
            arduinoService.sendData(data)
            ResponseEntity.ok("Data sent successfully")
        } catch (e: IOException) {
            e.printStackTrace()
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send data")
        }
    }

    @GetMapping("/receive")
    fun receiveData(): ResponseEntity<String> {
        return try {
            val receivedData = arduinoService.receiveData()
            ResponseEntity.ok(receivedData)
        } catch (e: IOException) {
            e.printStackTrace()
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to receive data")
        }
    }

}
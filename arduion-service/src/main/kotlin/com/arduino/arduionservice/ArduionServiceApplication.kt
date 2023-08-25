package com.arduino.arduionservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ArduionServiceApplication

fun main(args: Array<String>) {
	runApplication<ArduionServiceApplication>(*args)
}

package com.arduino.arduionservice.service


import com.fazecast.jSerialComm.SerialPort
import org.springframework.stereotype.Service
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

@Service
class ArduinoService {
    private val PORT_NAME = "/dev/ttyUSB0" // Adjust this to your Arduino's port
    private val BAUD_RATE = 9600
    private var serialPort: SerialPort? = null
    private var input: InputStream? = null
    private var output: OutputStream? = null

    @Throws(IOException::class)
    fun connect() {
        serialPort = SerialPort.getCommPort(PORT_NAME)
        serialPort!!.openPort()
        serialPort!!.setBaudRate(BAUD_RATE)
        serialPort!!.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0)
        input = serialPort!!.inputStream
        output = serialPort!!.outputStream
    }

    fun disconnect() {
        serialPort?.closePort()
    }

    @Throws(IOException::class)
    fun sendData(data: String) {
        output?.write(data.toByteArray())
    }

    @Throws(IOException::class)
    fun receiveData(): String {
        val buffer = ByteArray(1024)
        val len = input?.read(buffer) ?: 0
        return String(buffer, 0, len)
    }
}

import React, { useState, useEffect } from 'react';

function SensorData() {
  const [temperature, setTemperature] = useState(null);
  const [humidity, setHumidity] = useState(null);

  useEffect(() => {
    // Fetch temperature data from the Spring Boot API
    fetch('http://localhost:8080/sensors/temperature?sensorId=sensor1')
      .then((response) => response.json())
      .then((data) => setTemperature(data.reading));

    // Fetch humidity data from the Spring Boot API
    fetch('http://localhost:8080/sensors/humidity?sensorId=sensor2')
      .then((response) => response.json())
      .then((data) => setHumidity(data.reading));
  }, [temperature,humidity]);

  return (
    <div>
      <h2>Sensor Data</h2>
      <p>Temperature: {temperature} °C</p>
      <p>Humidity: {humidity} %</p>
    </div>
  );
}

export default SensorData;

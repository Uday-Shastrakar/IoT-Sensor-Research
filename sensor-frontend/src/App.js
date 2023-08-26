// src/App.js
import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [sensorData, setSensorData] = useState({
    temperature: null,
    humidity: null,
    loading: true,
    error: null,
  });

  const fetchData = () => {
    // Fetch temperature data from the Spring Boot API
    fetch('http://localhost:8080/sensors/temperature?sensorId=sensor1')
      .then((response) => response.json())
      .then((data) => {
        setSensorData((prevData) => ({
          ...prevData,
          temperature: data.reading,
        }));
      })
      .catch((error) => {
        console.error("Error fetching temperature data:", error);
        setSensorData((prevData) => ({
          ...prevData,
          error: "Error fetching temperature data. Please try again later.",
        }));
      });

    // Fetch humidity data from the Spring Boot API
    fetch('http://localhost:8080/sensors/humidity?sensorId=sensor2')
      .then((response) => response.json())
      .then((data) => {
        setSensorData((prevData) => ({
          ...prevData,
          humidity: data.reading,
          loading: false,
        }));
      })
      .catch((error) => {
        console.error("Error fetching humidity & temprature data:", error);
        setSensorData((prevData) => ({
          ...prevData,
          error: "Error fetching humidity & temperature data. Please try again later.",
          loading: false,
        }));
      });
  };

  useEffect(() => {
    // Fetch data initially when the component mounts
    fetchData();

    // Set up a timer to fetch data every 5 seconds (adjust the interval as needed)
    const intervalId = setInterval(fetchData, 1000);

    return () => {
      // Clear the interval when the component unmounts
      clearInterval(intervalId);
    };
  }, []); // Empty dependency array to run this effect only once on mount

  const { temperature, humidity, loading, error } = sensorData;

  return (
    <div className="App">
      <header className="App-header">
        <h1>Sensor Data</h1>
        {loading ? (
          <p>Loading...</p>
        ) : error ? (
          <p>{error}</p>
        ) : (
          <>
            <p>Temperature: {temperature} °C</p>
            <p>Humidity: {humidity} %</p>
          </>
        )}
      </header>
    </div>
  );
}

export default App;

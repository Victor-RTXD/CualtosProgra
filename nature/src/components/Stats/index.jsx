import { useState } from "react";
import axios from "axios";

export default function Stats() {
  const [region, setRegion] = useState("");
  const [weather, setWeather] = useState(null);
  const [airQuality, setAirQuality] = useState(null);
  const [error, setError] = useState(null);

  const API_KEY = "ce3d6e809120526fcbec669ef2a135f2"; // Reemplaza con tu API Key

  const fetchStats = async () => {
    try {
      setError(null);

      // Obtener coordenadas desde el nombre de la ciudad
      const geoRes = await axios.get(
        `https://api.openweathermap.org/geo/1.0/direct?q=${region}&limit=1&appid=${API_KEY}`
      );

      if (geoRes.data.length === 0) {
        setError("Ciudad no encontrada.");
        return;
      }

      const { lat, lon } = geoRes.data[0];

      // Obtener clima
      const weatherRes = await axios.get(
        `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}&units=metric&lang=es`
      );
      setWeather(weatherRes.data);

      // Obtener calidad del aire
      const airRes = await axios.get(
        `https://api.openweathermap.org/data/2.5/air_pollution?lat=${lat}&lon=${lon}&appid=${API_KEY}`
      );
      setAirQuality(airRes.data.list[0]);
    } catch (err) {
      setError("Error al obtener los datos.");
      console.error(err);
    }
  };

  return (
    <div className="bg-white text-gray-800 p-6 rounded-xl shadow-md max-w-xl mx-auto mt-8">
      <h2 className="text-2xl font-bold mb-4 text-center">Clima y Calidad del Aire</h2>
      
      <div className="flex gap-2 mb-4">
        <input
          type="text"
          placeholder="Ingresa una ciudad"
          value={region}
          onChange={(e) => setRegion(e.target.value)}
          className="flex-1 p-2 border rounded-lg"
        />
        <button
          onClick={fetchStats}
          className="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600"
        >
          Buscar
        </button>
      </div>

      {error && <div className="text-red-500 mb-4">{error}</div>}

      {weather && (
        <div className="bg-blue-100 p-4 rounded-lg mb-4">
          <h3 className="text-lg font-semibold mb-2">Clima en {weather.name}</h3>
          <p><strong>Temperatura:</strong> {weather.main.temp} °C</p>
          <p><strong>Sensación térmica:</strong> {weather.main.feels_like} °C</p>
          <p><strong>Humedad:</strong> {weather.main.humidity} %</p>
          <p><strong>Condición:</strong> {weather.weather[0].description}</p>
        </div>
      )}

      {airQuality && (
        <div className="bg-green-100 p-4 rounded-lg">
          <h3 className="text-lg font-semibold mb-2">Calidad del Aire</h3>
          <p><strong>Índice de calidad del aire (AQI):</strong> {airQuality.main.aqi}</p>
          <p><strong>CO:</strong> {airQuality.components.co} µg/m³</p>
          <p><strong>NO<sub>2</sub>:</strong> {airQuality.components.no2} µg/m³</p>
          <p><strong>PM2.5:</strong> {airQuality.components.pm2_5} µg/m³</p>
        </div>
      )}
    </div>
  );
}

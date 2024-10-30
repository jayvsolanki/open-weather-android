package com.example.testopenweatherapp.model

data class WeatherResponse(
    val name: String, // City name
    val main: Main,
    val weather: List<Weather>
)

data class Main(
    val temp: Double, // Temperature
    val humidity: Int // Humidity percentage
)

data class Weather(
    val description: String // Weather condition description
)


package com.example.testopenweatherapp.repository

import com.example.testopenweatherapp.model.WeatherResponse
import com.example.testopenweatherapp.network.RetrofitInstance
import retrofit2.Response

class WeatherRepository {
    private val api = RetrofitInstance.api

    // Function to fetch weather data by city name
    suspend fun fetchWeather(cityName: String, apiKey: String): Response<WeatherResponse> {
        return api.getWeatherByCity(cityName, apiKey)
    }
}

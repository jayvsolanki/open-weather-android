package com.example.testopenweatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testopenweatherapp.model.WeatherResponse
import com.example.testopenweatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    // LiveData for holding weather data
    val weatherData: MutableLiveData<WeatherResponse> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    // Function to fetch weather data based on city name
    fun getWeather(cityName: String, apiKey: String) {
        viewModelScope.launch {
            val response: Response<WeatherResponse> = repository.fetchWeather(cityName, apiKey)
            if (response.isSuccessful) {
                weatherData.postValue(response.body())
            } else {
                error.postValue("Error: ${response.message()}")
            }
        }
    }
}

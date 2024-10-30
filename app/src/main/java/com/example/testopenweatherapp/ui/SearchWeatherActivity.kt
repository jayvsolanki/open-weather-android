package com.example.testopenweatherapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testopenweatherapp.R
import com.example.testopenweatherapp.databinding.ActivitySearchWeatherBinding
import com.squareup.picasso.Picasso
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class SearchWeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchWeatherBinding
    private val apiKey = "3e46efd158bf1a26530f88f8498ae2f8"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            val city = binding.etCity.text.toString().trim()
            if (city.isNotEmpty()) {
                fetchWeather(city)
            } else {
                Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchWeather(city: String) {
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$apiKey&units=metric"
        val request = Request.Builder().url(url).build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val a = response

                response.body?.string()?.let {
                    val jsonResponse = JSONObject(it)
                    val main = jsonResponse.getJSONObject("main")
                    val weatherArray = jsonResponse.getJSONArray("weather").getJSONObject(0)

                    val cityName = jsonResponse.getString("name")
                    val temperature = main.getDouble("temp")
                    val humidity = main.getInt("humidity")
                    val description = weatherArray.getString("description")
                    val iconCode = weatherArray.getString("icon")

                    runOnUiThread {
                        binding.tvWeatherInfo.text = "City: $cityName\n" +
                                "Temperature: $temperature°C\n" +
                                "Humidity: $humidity%\n" +
                                "Description: $description"

                        val iconUrl = "http://openweathermap.org/img/wn/$iconCode@2x.png"
                        Picasso.get().load(iconUrl).into(binding.weatherIcon)
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@SearchWeatherActivity, "Failed to fetch weather data", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}


//
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.Observer
////import androidx.viewbinding.BuildConfig
//import com.example.testopenweatherapp.R
//import com.example.testopenweatherapp.repository.WeatherRepository
//import com.example.testopenweatherapp.viewmodel.WeatherViewModel
//import com.example.testopenweatherapp.viewmodel.WeatherViewModelFactory
//import com.example.testopenweatherapp.databinding.ActivitySearchWeatherBinding
//import kotlin.properties.ReadOnlyProperty
//
//class SearchWeatherActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivitySearchWeatherBinding
//
//    private val repository = WeatherRepository()
//    private val viewModelFactory = WeatherViewModelFactory(repository)
//    private val viewModel: WeatherViewModel by viewModels { viewModelFactory }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Initialize View Binding
//        binding = ActivitySearchWeatherBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val apiKey = "3e46efd158bf1a26530f88f8498ae2f8" // BuildConfig.OPENWEATHER_API_KEY
//
//        binding.btnSearch.setOnClickListener {
//            val city = binding.etCity.text.toString().trim()
//            if (city.isNotEmpty()) {
//                viewModel.getWeather(city, apiKey)
//            } else {
//                Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        viewModel.weatherData.observe(this, Observer { weather ->
//            binding.tvWeatherInfo.text = "City: ${weather.name}\n" +
//                    "Temperature: ${weather.main.temp}°C\n" +
//                    "Humidity: ${weather.main.humidity}%\n" +
//                    "Description: ${weather.weather[0].description}"
//        })
//
//        viewModel.error.observe(this, Observer { errorMessage ->
//            binding.tvWeatherInfo.text = errorMessage
//        })
//    }
//}



package com.example.testopenweatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testopenweatherapp.ui.SearchWeatherActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_search_weather)
    }
//    super.onCreate(savedInstanceState)
//    startActivity(Intent(this, SearchWeatherActivity::class.java))
//    finish()}
}
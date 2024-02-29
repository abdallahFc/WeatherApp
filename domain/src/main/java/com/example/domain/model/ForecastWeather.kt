package com.example.domain.model

data class ForecastWeather(
    val maxTemperature: Double,
    val minTemperature: Double,
    val condition: String,
    val date: String,
    val weatherIcon: Int
)

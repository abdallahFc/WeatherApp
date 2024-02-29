package com.example.domain.model

data class CurrentWeather(
    val temperature: Double,
    val condition: String,
    val date: String,
    val weatherIcon: Int
)

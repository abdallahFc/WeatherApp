package com.example.domain.repository

import com.example.domain.model.CurrentWeather
import com.example.domain.model.ForecastWeather


interface WeatherRepository {
    suspend fun getWeather(city: String): List<CurrentWeather>
    suspend fun getForecast(city: String): List<ForecastWeather>
}
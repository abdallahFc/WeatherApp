package com.example.data.repository

import android.util.Log
import com.example.data.source.remote.mapper.toCurrentWeather
import com.example.data.source.remote.mapper.toWeatherForecast
import com.example.domain.repository.WeatherRepository
import com.example.data.source.remote.network.WeatherService
import com.example.domain.model.CurrentWeather
import com.example.domain.model.ForecastWeather
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val weatherService: WeatherService
) : WeatherRepository, BaseRepository() {
    override suspend fun getWeather(city: String): List<CurrentWeather> {
        return wrapResponse {
            weatherService.getCurrentWeather(city)
        }.map { it.toCurrentWeather() }
    }

    override suspend fun getForecast(city: String): List<ForecastWeather> {
        return wrapResponse {
            weatherService.getCurrentWeatherForecast(city)
        }.dailyForecasts?.map { it.toWeatherForecast() } ?: emptyList()
    }
}
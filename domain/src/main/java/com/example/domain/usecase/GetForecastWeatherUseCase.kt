package com.example.domain.usecase

import com.example.domain.model.ForecastWeather
import com.example.domain.repository.WeatherRepository
import javax.inject.Inject

class GetForecastWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): List<ForecastWeather> {
        return repository.getForecast(city)
    }
}
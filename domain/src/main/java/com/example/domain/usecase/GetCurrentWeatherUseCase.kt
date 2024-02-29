package com.example.domain.usecase

import com.example.domain.model.CurrentWeather
import com.example.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): List<CurrentWeather> {
        return repository.getWeather(city)
    }
}
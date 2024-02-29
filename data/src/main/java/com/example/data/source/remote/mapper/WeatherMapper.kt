package com.example.data.source.remote.mapper

import com.example.data.source.remote.dto.DailyForecast
import com.example.data.source.remote.dto.WeatherDto
import com.example.data.source.remote.dto.WeatherForecastDto
import com.example.domain.model.CurrentWeather
import com.example.domain.model.ForecastWeather

fun WeatherDto.toCurrentWeather(): CurrentWeather {
    val temperature = this.temperature?.metric?.value ?: 0.0
    val condition = this.weatherText ?: ""
    val weatherIcon = this.weatherIcon ?: 0
    val date = this.localObservationDateTime ?: ""
    return CurrentWeather(
        temperature = temperature,
        condition = condition,
        weatherIcon = weatherIcon,
        date = date
    )
}

fun DailyForecast.toWeatherForecast(): ForecastWeather {
   val max = this.temperature?.maximum?.value ?: 0.0
    val min = this.temperature?.minimum?.value ?: 0.0
    val condition = this.day?.iconPhrase ?: ""
    val weatherIcon = this.day?.icon ?: 0
    val date = this.date ?: ""
    return ForecastWeather (
        maxTemperature = max,
        minTemperature = min,
        condition = condition,
        weatherIcon = weatherIcon,
        date = date
    )
}
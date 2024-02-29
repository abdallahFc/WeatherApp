package com.example.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherForecastDto(
    @SerializedName("DailyForecasts") val dailyForecasts: List<DailyForecast>?,
    @SerializedName("Headline") val headline: Headline?
)

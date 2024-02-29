package com.example.data.source.remote.network


import com.example.core.util.ApiConstants.CITY_ID_PATH
import com.example.core.util.ApiConstants.CURRENT_WEATHER_ENDPOINT
import com.example.core.util.ApiConstants.WEATHER_FORECAST_ENDPOINT
import com.example.data.source.remote.dto.WeatherDto
import com.example.data.source.remote.dto.WeatherForecastDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    @GET(CURRENT_WEATHER_ENDPOINT)
    suspend fun getCurrentWeather(
        @Path(CITY_ID_PATH) locationKey: String?,
    ): Response<List<WeatherDto>>

    @GET(WEATHER_FORECAST_ENDPOINT)
    suspend fun getCurrentWeatherForecast(
        @Path(CITY_ID_PATH) locationKey: String?,
    ): Response<WeatherForecastDto>
}
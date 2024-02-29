package com.example.domain.repository

import com.example.domain.model.City

interface CityRepository {

    suspend fun searchCityByName(cityName: String): List<City>

    suspend fun getAllCities(): List<City>

    suspend fun saveCity(city: City)

}

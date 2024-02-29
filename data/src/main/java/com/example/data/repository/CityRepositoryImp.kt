package com.example.data.repository

import com.example.data.source.local.CityDao
import com.example.data.source.local.mapper.toCity
import com.example.data.source.local.mapper.toCityEntity
import com.example.data.source.remote.mapper.toCity
import com.example.data.source.remote.network.CityService
import com.example.domain.model.City
import com.example.domain.repository.CityRepository
import javax.inject.Inject

class CityRepositoryImp @Inject constructor(
    private val cityService: CityService,
    private val cityDao: CityDao
) : CityRepository, BaseRepository() {
    override suspend fun searchCityByName(cityName: String): List<City> {
        return wrapResponse { cityService.searchOnCity(cityName) }.map { it.toCity() }
    }

    override suspend fun getAllCities(): List<City> {
        return cityDao.getCities().map { it.toCity() }
    }

    override suspend fun saveCity(city: City) {
        cityDao.insertCity(city.toCityEntity())
    }
}
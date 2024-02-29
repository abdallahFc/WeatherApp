package com.example.domain.usecase

import com.example.domain.model.City
import com.example.domain.repository.CityRepository
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(
    private val repository: CityRepository
) {
    suspend operator fun invoke(city: City) {
        return repository.saveCity(city)
    }
}
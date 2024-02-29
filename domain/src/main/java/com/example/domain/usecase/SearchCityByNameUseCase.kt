package com.example.domain.usecase

import com.example.domain.model.City
import com.example.domain.repository.CityRepository
import javax.inject.Inject

class SearchCityByNameUseCase @Inject constructor(
    private val repository: CityRepository
) {
    suspend operator fun invoke(city: String): List<City> {
        return repository.searchCityByName(city)
    }
}
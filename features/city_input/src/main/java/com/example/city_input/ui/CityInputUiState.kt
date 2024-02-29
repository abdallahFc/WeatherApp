package com.example.city_input.ui

import com.example.core.util.ErrorHandler
import com.example.domain.model.City

data class CityInputUiState(
    val isLoading: Boolean = false,
    val error: ErrorHandler? = null,
    val isError: Boolean = false,
    val searchQuery: String = "",
    val cityList: List<CityUiState> = emptyList(),
)

data class CityUiState(
    val city: String = "",
    val country: String = "",
    val locationKey: String = ""
)

fun City.toUiModel() = CityUiState(
    city = city,
    country = country,
    locationKey = locationKey
)
fun CityUiState.toDomainModel() = City(
    city = city,
    country = country,
    locationKey = locationKey
)

fun CityInputUiState.contentScreen() = !this.isLoading && !this.isError && this.cityList.isNotEmpty()

fun CityInputUiState.emptyPlaceHolder() = this.cityList.isEmpty() &&
        !this.isError && !this.isLoading
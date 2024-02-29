package com.example.city_input.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core.util.ErrorHandler
import com.example.core.base.BaseViewModel
import com.example.core.util.DispatcherProvider
import com.example.domain.model.City
import com.example.domain.usecase.GetSavedCityUseCase
import com.example.domain.usecase.SaveCityUseCase
import com.example.domain.usecase.SearchCityByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityInputViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityByNameUseCase,
    private val getSavedCityUseCase: GetSavedCityUseCase,
    private val saveCityUseCase: SaveCityUseCase,
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel<CityInputUiState>(CityInputUiState()) {

    val actionStream = MutableSharedFlow<String>()

    init {
        observeKeyword()
        getAllCitiesFromDb()
    }

    fun getAllCitiesFromDb() {
        viewModelScope.launch {
            tryToExecute(
                { getSavedCityUseCase() },
                ::getAllCitiesSuccess,
                ::getAllCitiesError,
                dispatcherProvider.io
            )
        }
    }

    private fun getAllCitiesSuccess(cityList: List<City>) {
        updateState {
            it.copy(
                isLoading = false,
                cityList = cityList.map { it.toUiModel() },
            )
        }
    }

    private fun getAllCitiesError(error: ErrorHandler) {
        updateState { it.copy(isLoading = false, error = error, isError = true) }
    }

    private fun searchForCities(keyword: String) {
        updateState { it.copy(isLoading = true, isError = false, error = null) }
        viewModelScope.launch {
            tryToExecute(
                { searchCityUseCase(keyword) },
                ::searchSuccess,
                ::searchError,
                dispatcherProvider.io
            )
        }
    }

    private fun searchSuccess(cityList: List<City>) {
        updateState {
            it.copy(
                isLoading = false,
                cityList = cityList.map { it.toUiModel() },
            )
        }
    }

    private fun searchError(error: ErrorHandler) {
        updateState { it.copy(isLoading = false, error = error, isError = true) }
    }

    fun onSearchTextChange(text: String) {
        updateState { it.copy(searchQuery = text) }
        viewModelScope.launch { actionStream.emit(text) }
    }

    private fun observeKeyword() {
        viewModelScope.launch {
            actionStream
                .debounce(800)
                .distinctUntilChanged()
                .filter { keyword -> keyword.isNotBlank() }
                .collect {
                    searchForCities(it)
                }
        }
    }

    fun onCitySelected(city: CityUiState) {
        viewModelScope.launch {
            saveCityUseCase(city.toDomainModel())
        }
    }

    private fun updateState(update: (CityInputUiState) -> CityInputUiState) {
        _state.update(update)
    }
}

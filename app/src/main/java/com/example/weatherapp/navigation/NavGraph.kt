package com.example.weatherapp.navigation

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.city_input.ui.CityInputScreen
import com.example.city_input.ui.CityInputViewModel
import com.example.current_weather.ui.CurrentWeatherScreen
import com.example.current_weather.ui.CurrentWeatherViewModel
import com.example.forecast_weather.ForecastWeatherScreen
import com.example.forecast_weather.ForecastWeatherViewModel
import com.example.weatherapp.navigation.Constants.Companion.LOCATION_KEY

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainNavGraph() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.semantics { testTagsAsResourceId = true },
        navController = navController,
        startDestination = Screen.CityInput.route,
    ) {
        composable(
            route = Screen.CityInput.route
        ) {
            val viewModel: CityInputViewModel = hiltViewModel()
            val state =viewModel.state.collectAsState()
            CityInputScreen(
                state = state.value,
               onSearchTextChange = viewModel::onSearchTextChange,
            ){
                viewModel.onCitySelected(it)
                navController.navigate(
                    Screen.CurrentWeather.passedLocationKey(it.locationKey)
                )
            }
        }

        composable(
            route = Screen.CurrentWeather.route,
            arguments = listOf(
                navArgument(LOCATION_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            val location =it.arguments?.getString(LOCATION_KEY)
            if (location != null) {
                val viewModel: CurrentWeatherViewModel = hiltViewModel()
                LaunchedEffect(key1 = Unit){
                    viewModel.getCurrentWeather(location)
                }
                val state =viewModel.state.collectAsState()
                CurrentWeatherScreen(
                    state = state.value,
                ){
                    navController.navigate(
                        Screen.ForecastWeather.passedLocationKey(location)
                    )
                }
            }
        }

        composable(
            route = Screen.ForecastWeather.route,
            arguments = listOf(
                navArgument(LOCATION_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            val location =it.arguments?.getString(LOCATION_KEY)
            if (location != null) {
                val viewModel: ForecastWeatherViewModel = hiltViewModel()
                LaunchedEffect(key1 = Unit){
                   viewModel.getForecastWeather(location)
                }
                val state =viewModel.state.collectAsState()
                ForecastWeatherScreen(state = state.value)
            }
        }
    }
}
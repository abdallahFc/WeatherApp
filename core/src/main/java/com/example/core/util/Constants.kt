package com.example.core.util

object ApiConstants {
    const val CITY_ID_PATH = "locationKey"
    const val BASE_URL = "https://dataservice.accuweather.com/"
    const val API_KEY="GgSGlAH9TZntj2x3gnRSksIN31RKYuAG"

    const val WEATHER_FORECAST_ENDPOINT = "forecasts/v1/daily/5day/{$CITY_ID_PATH}"
    const val SEARCH_CITY_ENDPOINT = "locations/v1/cities/autocomplete"
    const val CURRENT_WEATHER_ENDPOINT = "currentconditions/v1/{$CITY_ID_PATH}"

    const val QUERY_API_KEY = "apikey"
    const val QUERY_CITY_NAME = "q"

    const val ERROR_UNKNOWN_SERVER = "server error"
    const val ERROR_NETWORK = "No Internet Connection"
    const val ERROR_UNEXPECTED = "An unexpected error occurred"

}

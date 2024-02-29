package com.example.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class TemperatureForecast(
    @SerializedName("Maximum") val maximum: Imperial?,
    @SerializedName("Minimum") val minimum: Imperial?
)

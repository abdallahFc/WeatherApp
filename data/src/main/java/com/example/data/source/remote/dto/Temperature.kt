package com.example.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("Imperial") val imperial: Imperial?,
    @SerializedName("Metric") val metric: Metric?
)

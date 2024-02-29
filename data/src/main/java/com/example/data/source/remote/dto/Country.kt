package com.example.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("ID") val id: String?,
    @SerializedName("LocalizedName") val localizedName: String?
)

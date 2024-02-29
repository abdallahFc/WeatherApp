package com.example.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class AdministrativeArea(
    @SerializedName("ID") val id: String?,
    @SerializedName("LocalizedName") val localizedName: String?
)

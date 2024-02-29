package com.example.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("HasPrecipitation") val hasPrecipitation: Boolean?,
    @SerializedName("Icon") val icon: Int?,
    @SerializedName("IconPhrase") val iconPhrase: String?,
    @SerializedName("PrecipitationIntensity") val precipitationIntensity: String?,
    @SerializedName("PrecipitationType") val precipitationType: String?
)

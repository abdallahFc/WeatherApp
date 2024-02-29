package com.example.data.source.remote.mapper

import com.example.data.source.remote.dto.CityDto
import com.example.domain.model.City

fun CityDto.toCity(): City {
    return City(
        city = this.localizedName ?: "",
        country = this.country?.localizedName ?: "",
        locationKey = this.key ?: ""
    )
}

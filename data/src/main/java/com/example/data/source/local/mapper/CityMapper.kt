package com.example.data.source.local.mapper

import com.example.data.source.local.CityEntity
import com.example.data.source.remote.dto.CityDto
import com.example.domain.model.City

fun CityEntity.toCity(): City {
    return City(
        city=city,
        country=country,
        locationKey=locationKey
    )
}
fun City.toCityEntity(): CityEntity {
    return CityEntity(
        city=city,
        country=country,
        locationKey=locationKey
    )
}

package com.example.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities_table")
data class CityEntity(
    @PrimaryKey
    val locationKey: String,
    val city: String,
    val country: String,
)

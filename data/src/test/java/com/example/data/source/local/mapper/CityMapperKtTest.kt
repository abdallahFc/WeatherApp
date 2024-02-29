package com.example.data.source.local.mapper

import com.example.data.source.local.CityEntity
import com.example.domain.model.City
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class CityMapperKtTest(
    private val input: CityEntity,
    private val expected: City
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: Model = {0}, Expected = {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    CityEntity(locationKey = "1", city = "City", country = "Country"),
                    City(city = "City", country = "Country", locationKey = "1")
                )
            )
        }
    }

    @Test
    fun mapToDomainModel() {
        val result = input.toCity()
        assertEquals(expected, result)
    }
}

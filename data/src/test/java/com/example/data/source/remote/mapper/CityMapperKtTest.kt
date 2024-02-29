package com.example.data.source.remote.mapper

import com.example.data.source.remote.dto.AdministrativeArea
import com.example.data.source.remote.dto.CityDto
import com.example.data.source.remote.dto.Country
import com.example.domain.model.City
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class CityMapperKtTest(
    private val input: CityDto,
    private val expected: City
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: Model = {0}, Expected = {1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    CityDto(
                        version = 1,
                        key = "1",
                        type = "City",
                        rank = 1,
                        localizedName = "City",
                        country = Country(
                            id = "1",
                            localizedName = "Country"
                        ),
                        administrativeArea = AdministrativeArea(
                            id = "1",
                            localizedName = "Fake Administrative Area"
                        )
                    ),
                    City(
                        city = "City",
                        country = "Country",
                        locationKey = "1"
                    )
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

package com.example.data.repository

import com.example.data.source.local.CityDao
import com.example.data.source.local.CityEntity
import com.example.data.source.local.mapper.toCityEntity
import com.example.data.source.remote.dto.AdministrativeArea
import com.example.data.source.remote.dto.CityDto
import com.example.data.source.remote.dto.Country
import com.example.data.source.remote.network.CityService
import com.example.domain.model.City
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class CityRepositoryImpTest {

    private lateinit var cityRepository: CityRepositoryImp
    private lateinit var cityService: CityService
    private lateinit var cityDao: CityDao

    @Before
    fun setup() {
        cityService = mockk()
        cityDao = mockk()
        cityRepository = CityRepositoryImp(cityService, cityDao)
    }
    @Test
    fun `getAllCities() when database has data, then it should return a list of City`() = runBlocking {
        // Given
        val expected = listOf(
            City("1", "TestCity", "TestCountry"),
            City("2", "TestCity2", "TestCountry2")
        )

        coEvery { cityDao.getCities() } returns expected.map { it.toCityEntity() }

        // When
        val result = cityRepository.getAllCities()

        // Then
        assertEquals(expected, result)
    }
    @Test
    fun `saveCity() when called, it should insert the city into the database`() = runBlocking {
        // Given
        val cityToSave = City("1", "TestCity", "TestCountry")
        val cityEntityToSave = cityToSave.toCityEntity()

        // When
        coEvery { cityDao.insertCity(cityEntityToSave) } returns Unit
        cityRepository.saveCity(cityToSave)

        // Then
        coVerify { cityDao.insertCity(cityEntityToSave) }
    }

}

package com.example.data.source.remote.network

import com.example.core.util.ApiConstants.QUERY_CITY_NAME
import com.example.core.util.ApiConstants.SEARCH_CITY_ENDPOINT
import com.example.data.source.remote.dto.CityDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CityService {
    @GET(SEARCH_CITY_ENDPOINT)
    suspend fun searchOnCity(@Query(QUERY_CITY_NAME) query: String?): Response<List<CityDto>>
}
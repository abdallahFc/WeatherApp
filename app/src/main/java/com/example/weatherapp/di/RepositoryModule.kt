package com.example.weatherapp.di

import com.example.data.repository.CityRepositoryImp
import com.example.data.repository.WeatherRepositoryImp
import com.example.domain.repository.CityRepository
import com.example.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideWeatherRepository(repository: WeatherRepositoryImp): WeatherRepository{
        return repository
    }
    @Singleton
    @Provides
    fun provideCityRepository(repository: CityRepositoryImp): CityRepository{
        return repository
    }
}
package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.data.source.local.CityDao
import com.example.data.source.local.CityDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesRoomDatabase(
        @ApplicationContext context: Context,
    ): CityDataBase {
        return Room.databaseBuilder(context, CityDataBase::class.java, "CityDatabase")
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(database: CityDataBase): CityDao {
        return database.cityDao
    }

}
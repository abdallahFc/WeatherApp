package com.example.weatherapp.di

import com.example.core.util.ApiConstants.API_KEY
import com.example.core.util.ApiConstants.BASE_URL
import com.example.core.util.ApiConstants.QUERY_API_KEY
import com.example.data.source.remote.network.CityService
import com.example.data.source.remote.network.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level =
            HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val builder = OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val original: Request = chain.request()

                val originalHttpUrl: HttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter(QUERY_API_KEY, API_KEY)
                    .build()

                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)

                val request: Request = requestBuilder.build()
                chain.proceed(request)
            }

        return builder.build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideCityApi(retrofit: Retrofit): CityService {
        return retrofit.create(CityService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

}

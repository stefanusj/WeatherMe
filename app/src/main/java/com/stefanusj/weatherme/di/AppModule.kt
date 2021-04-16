package com.stefanusj.weatherme.di

import com.stefanusj.weatherme.repository.WeatherRepository
import com.stefanusj.weatherme.repository.database.LocalService
import com.stefanusj.weatherme.repository.network.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideWeatherRepository(
        localService: LocalService,
        webService: WebService
    ): WeatherRepository = WeatherRepository(localService, webService)
}
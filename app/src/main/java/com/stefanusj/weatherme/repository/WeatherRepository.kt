package com.stefanusj.weatherme.repository

import com.stefanusj.weatherme.repository.database.LocalService
import com.stefanusj.weatherme.repository.model.OpenWeatherDatabase
import com.stefanusj.weatherme.repository.network.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val localService: LocalService,
    private val webService: WebService
) {

    suspend fun fetchCurrentWeather(
        latitude: Double,
        longitude: Double,
    ): OpenWeatherDatabase = withContext(Dispatchers.IO) {
        val response = webService.getCurrentWeather(latitude, longitude)
        val weather = response.toOpenWeatherDatabase()
        localService.insertOpenWeather(weather)
        return@withContext weather
    }

    fun fetchWeathers(
    ): Flow<List<OpenWeatherDatabase>> = localService.getOpenWeathers()
}
package com.stefanusj.weatherme.usecase

import android.content.Context
import androidx.lifecycle.liveData
import com.google.android.gms.location.LocationServices
import com.stefanusj.weatherme.helper.await
import com.stefanusj.weatherme.repository.Result
import com.stefanusj.weatherme.repository.WeatherRepository
import com.stefanusj.weatherme.repository.model.OpenWeatherDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: WeatherRepository
) {

    operator fun invoke() = liveData<Result<OpenWeatherDatabase>> {
        try {
            val location = LocationServices.getFusedLocationProviderClient(context).await()
            val response = repository.fetchCurrentWeather(location.latitude, location.longitude)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

}
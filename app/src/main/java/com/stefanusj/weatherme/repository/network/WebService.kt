package com.stefanusj.weatherme.repository.network

import com.stefanusj.weatherme.BuildConfig
import com.stefanusj.weatherme.repository.model.OpenWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

        private const val IMAGE_URL = "https://openweathermap.org/img/wn/:image@:size.png"
        fun imageUrl(image: String, size: String = "4x") =
            IMAGE_URL
                .replace(":image", image)
                .replace(":size", size)
    }

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric",
        @Query("lang") language: String = "en",
        @Query("appid") appId: String = BuildConfig.OPEN_WEATHER_APP_ID
    ): OpenWeatherResponse

}
package com.stefanusj.weatherme.repository.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class OpenWeatherResponse(
    val id: Long,
    val name: String,
    @SerializedName("coord")
    @Embedded val coordinate: Coordinate,
    @SerializedName("weather")
    val weathers: List<Weather>,
    val main: Main,
    val wind: Wind,
    @SerializedName("clouds")
    val cloud: Cloud,
    val sys: Sys,
    @SerializedName("dt")
    val datetime: Long,
    val timezone: Long,
) {

    fun toOpenWeatherDatabase() = OpenWeatherDatabase(
        name = name,
        coordinate = coordinate,
        weather = weathers.first(),
        main = main,
        wind = wind,
        cloud = cloud,
        sys = sys,
        datetime = datetime,
        timezone = timezone
    )
}
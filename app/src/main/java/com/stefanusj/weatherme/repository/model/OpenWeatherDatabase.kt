package com.stefanusj.weatherme.repository.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "open_weather", indices = [Index("datetime", unique = true)])
data class OpenWeatherDatabase(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String,
    @Embedded(prefix = "coordinate_")
    val coordinate: Coordinate,
    @Embedded(prefix = "weather_")
    val weather: Weather,
    @Embedded(prefix = "main_")
    val main: Main,
    @Embedded(prefix = "wind_")
    val wind: Wind,
    @Embedded(prefix = "cloud_")
    val cloud: Cloud,
    @Embedded(prefix = "sys_")
    val sys: Sys,
    val datetime: Long,
    val timezone: Long,
) {

    val timestampMs get() = datetime * 1000
}
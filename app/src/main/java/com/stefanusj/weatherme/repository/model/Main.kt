package com.stefanusj.weatherme.repository.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Main(
    @ColumnInfo(name = "feels_like")
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp")
    val temperature: Double,
    @ColumnInfo(name = "temp_min")
    @SerializedName("temp_min")
    val temperatureMin: Double,
    @ColumnInfo(name = "temp_max")
    @SerializedName("temp_max")
    val temperatureMax: Double,
    val pressure: Int,
    val humidity: Int,
    @ColumnInfo(name = "sea_level")
    @SerializedName("sea_level")
    val seaLevel: Int,
    @ColumnInfo(name = "grnd_level")
    @SerializedName("grnd_level")
    val groundLevel: Int
) {

    companion object {
        const val DEGREE = "\u00B0"
        const val CELCIUS = "\u2103"
        const val HUMIDITY = "%"
    }
}
package com.stefanusj.weatherme.repository.model

import com.google.gson.annotations.SerializedName

data class Coordinate(
    @SerializedName("lon")
    val longitude: Double,
    @SerializedName("lat")
    val latitude: Double
)
package com.stefanusj.weatherme.repository.model

import com.google.gson.annotations.SerializedName

data class Wind(
    val speed: Double,
    @SerializedName("deg")
    val degrees: Int
) {

    companion object {
        const val WIND_SPEED = "\u33A7"
    }
}
package com.stefanusj.weatherme.repository.model

import com.stefanusj.weatherme.repository.network.WebService

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
) {

    val iconUrl get() = WebService.imageUrl(icon)

}
package com.meowplex.weather_app.model

import java.time.LocalDateTime

data class LocationModel(
    val city: String,
    val region: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val date: LocalDateTime,
)
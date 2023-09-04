package com.meowplex.weather_app.model

data class SearchLocationModel(
    val city: String,
    val region: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
)
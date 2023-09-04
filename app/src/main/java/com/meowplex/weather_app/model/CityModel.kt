package com.meowplex.weather_app.model

data class CityModel(
    val id: Long,
    val latitude: Double,
    val longitude: Double,
    val weather: WeatherPreviewModel,
    val isPrimary: Boolean = false
)
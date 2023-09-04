package com.meowplex.weather_app.model

data class WeatherPreviewModel(
    val city: String,
    val temp: TemperatureModel,
    val comment: String,
    val iconRes: String
)
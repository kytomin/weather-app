package com.meowplex.weather_app.model

data class CurrentWeatherModel(
    val city: String,
    val temp: TemperatureModel,
    val maxTemp: TemperatureModel,
    val minTemp: TemperatureModel,
    val comment: String,
)
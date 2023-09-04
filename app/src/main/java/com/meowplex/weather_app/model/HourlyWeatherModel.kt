package com.meowplex.weather_app.model

data class HourlyWeatherModel(
    val time: String,
    val temp: TemperatureModel,
    val humidity: String,
    val isDay: Boolean,
    val iconRes: String
)
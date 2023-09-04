package com.meowplex.weather_app.model

data class DailyWeatherModel(
    val dayOfWeek: String,
    val date: String,
    val isDay: Boolean,
    val iconRes: String,
    val maxTemp: TemperatureModel,
    val minTemp: TemperatureModel,

    )
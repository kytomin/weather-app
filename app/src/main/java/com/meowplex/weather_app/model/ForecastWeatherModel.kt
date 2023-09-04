package com.meowplex.weather_app.model

data class ForecastWeatherModel(
    val location: LocationModel,
    val current: CurrentWeatherModel,
    val daily: List<DailyWeatherModel>,
    val hourly: List<HourlyWeatherModel>,
    val aqiModel: AqiModel?,
    val astroModel: AstroModel,
    val helpfulInfoModel: HelpfulInfoModel
)
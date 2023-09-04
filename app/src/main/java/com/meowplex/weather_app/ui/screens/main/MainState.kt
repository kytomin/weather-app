package com.meowplex.weather_app.ui.screens.main

import com.meowplex.weather_app.model.*

data class MainState(
    val isPrimaryCity: Boolean = false,
    val location: LocationModel? = null,
    val currentWeather: CurrentWeatherModel? = null,
    val hourlyWeather: List<HourlyWeatherModel> = listOf(),
    val dailyWeather: List<DailyWeatherModel> = listOf(),
    val helpfulInfo: HelpfulInfoModel? = null,
    val aqi: AqiModel? = null,
    val astro: AstroModel? = null,
    val tempUnit: TemperatureUnits = TemperatureUnits.Celsius,
    val isLoading: Boolean = false,
    val error: String? = null
)
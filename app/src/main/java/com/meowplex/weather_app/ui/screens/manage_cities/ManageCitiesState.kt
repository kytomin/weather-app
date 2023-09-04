package com.meowplex.weather_app.ui.screens.manage_cities

import com.meowplex.weather_app.model.CityModel
import com.meowplex.weather_app.model.TemperatureUnits

data class ManageCitiesState(
    val cities: List<CityModel> = mutableListOf(),
    val tempUnit: TemperatureUnits = TemperatureUnits.Celsius,
)
package com.meowplex.weather_app.ui.screens.add_city

import com.meowplex.weather_app.model.SearchLocationModel

data class AddCityState(
    val inputValue: String = "",
    val currentCity: SearchLocationModel? = null,
    val loadingCurrentCity: Boolean = false,
    val cities: List<SearchLocationModel> = emptyList()
)
package com.meowplex.weather_app.dto.forecast

import com.google.gson.annotations.SerializedName
import com.meowplex.weather_app.dto.LocationDTO
import com.meowplex.weather_app.dto.current.CurrentDTO

data class ForecastWeatherResponse(
    @SerializedName("location") val location: LocationDTO,
    @SerializedName("current") val current: CurrentDTO,
    @SerializedName("forecast") val forecast: ForecastDTO,
)
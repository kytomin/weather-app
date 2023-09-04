package com.meowplex.weather_app.dto.current

import com.google.gson.annotations.SerializedName
import com.meowplex.weather_app.dto.LocationDTO

data class CurrentWeatherResponse(
    @SerializedName("location") val location: LocationDTO,
    @SerializedName("current") val current: CurrentDTO
)
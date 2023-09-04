package com.meowplex.weather_app.dto.forecast

import com.google.gson.annotations.SerializedName

data class ForecastDTO(
    @SerializedName("forecastday") val days: List<ForecastDayDTO>
)
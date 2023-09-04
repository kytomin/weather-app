package com.meowplex.weather_app.dto.forecast

import com.google.gson.annotations.SerializedName
import com.meowplex.weather_app.dto.AstroDTO
import com.meowplex.weather_app.dto.DayDTO
import com.meowplex.weather_app.dto.HourDTO

data class ForecastDayDTO(
    @SerializedName("date") val date: String,
    @SerializedName("date_epoch") val dateEpoch: Long,
    @SerializedName("day") val day: DayDTO,
    @SerializedName("astro") val astro: AstroDTO,
    @SerializedName("hour") val hour: List<HourDTO>,
)
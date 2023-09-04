package com.meowplex.weather_app.model

import com.meowplex.weather_app.ext.getProgress
import java.time.LocalTime

data class AstroModel(
    val sunrise: LocalTime?,
    val sunset: LocalTime?,
    val moonrise: LocalTime?,
    val moonset: LocalTime?,
    val currentTime: LocalTime,
    val moonPhase: String,
    val isMoonUp: Boolean,
    val isSunUp: Boolean,
) {

    val progressSun: Double = currentTime.getProgress(sunrise, sunset)
    val progressMoon: Double = currentTime.getProgress(moonrise, moonset)

}
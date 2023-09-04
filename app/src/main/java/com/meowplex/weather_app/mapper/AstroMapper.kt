package com.meowplex.weather_app.mapper

import com.meowplex.weather_app.dto.forecast.ForecastWeatherResponse
import com.meowplex.weather_app.model.AstroModel
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class AstroMapper : Mapper<ForecastWeatherResponse, AstroModel> {

    override fun transform(data: ForecastWeatherResponse): AstroModel {
        return AstroModel(
            sunrise = try {
                LocalTime.parse(
                    data.forecast.days[0].astro.sunrise,
                    DateTimeFormatter.ofPattern("hh:mm a")
                )
            } catch (e: Exception) {
                null
            },
            sunset = try {
                LocalTime.parse(
                    data.forecast.days[0].astro.sunset,
                    DateTimeFormatter.ofPattern("hh:mm a")
                )
            } catch (e: Exception) {
                null
            },
            moonrise = try {
                LocalTime.parse(
                    data.forecast.days[0].astro.moonrise,
                    DateTimeFormatter.ofPattern("hh:mm a")
                )
            } catch (e: Exception) {
                null
            },
            moonset = try {
                LocalTime.parse(
                    data.forecast.days[0].astro.moonset,
                    DateTimeFormatter.ofPattern("hh:mm a")
                )
            } catch (e: Exception) {
                null
            },
            currentTime = LocalTime.parse(
                data.location.localtime,
                DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm")
            ),
            isMoonUp = data.forecast.days[0].astro.isMoonUp != 0,
            isSunUp = data.forecast.days[0].astro.isSunUp != 0,
            moonPhase = data.forecast.days[0].astro.moonPhase,
        )
    }
}
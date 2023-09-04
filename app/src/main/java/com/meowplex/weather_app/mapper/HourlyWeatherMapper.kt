package com.meowplex.weather_app.mapper

import com.meowplex.weather_app.dto.HourDTO
import com.meowplex.weather_app.model.HourlyWeatherModel
import com.meowplex.weather_app.model.TemperatureModel

class HourlyWeatherMapper : Mapper<List<HourDTO>, List<HourlyWeatherModel>> {
    override fun transform(data: List<HourDTO>): List<HourlyWeatherModel> {
        return data.map {
            HourlyWeatherModel(
                time = it.time.substringAfter(" "),
                temp = TemperatureModel(celsius = it.tempC, fahrenheit = it.tempF),
                humidity = "${it.humidity}%",
                isDay = it.isDay != 0,
                iconRes = if (it.isDay != 0)
                    "day_${it.condition.code}"
                else
                    "night_${it.condition.code}"
            )
        }
    }

}
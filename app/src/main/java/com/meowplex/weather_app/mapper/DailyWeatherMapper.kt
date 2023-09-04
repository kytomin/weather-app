package com.meowplex.weather_app.mapper

import com.meowplex.weather_app.dto.forecast.ForecastWeatherResponse
import com.meowplex.weather_app.ext.toFormattedString
import com.meowplex.weather_app.model.DailyWeatherModel
import com.meowplex.weather_app.model.TemperatureModel
import java.util.*

class DailyWeatherMapper : Mapper<ForecastWeatherResponse, List<DailyWeatherModel>> {
    override fun transform(data: ForecastWeatherResponse): List<DailyWeatherModel> {
        return data.forecast.days.map {
            DailyWeatherModel(
                dayOfWeek = Date(it.dateEpoch * 1000).toFormattedString("EEEE"),
                date = Date(it.dateEpoch * 1000).toFormattedString(),
                isDay = data.current.isDay != 0,
                maxTemp = TemperatureModel(celsius = it.day.maxtempC, fahrenheit = it.day.maxtempF),
                minTemp = TemperatureModel(celsius = it.day.mintempC, fahrenheit = it.day.mintempF),
                iconRes = if (data.current.isDay != 0)
                    "day_${it.day.condition.code}"
                else
                    "night_${it.day.condition.code}"
            )
        }
    }
}
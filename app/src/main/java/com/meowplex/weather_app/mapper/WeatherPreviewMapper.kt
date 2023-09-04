package com.meowplex.weather_app.mapper

import com.meowplex.weather_app.dto.current.CurrentWeatherResponse
import com.meowplex.weather_app.model.TemperatureModel
import com.meowplex.weather_app.model.WeatherPreviewModel

class WeatherPreviewMapper : Mapper<CurrentWeatherResponse, WeatherPreviewModel> {
    override fun transform(data: CurrentWeatherResponse): WeatherPreviewModel {
        return WeatherPreviewModel(
            city = data.location.name,
            temp = TemperatureModel(celsius = data.current.tempC, fahrenheit = data.current.tempF),
            comment = data.current.condition.text,
            iconRes =
            if (data.current.isDay != 0)
                "day_${data.current.condition.code}"
            else
                "night_${data.current.condition.code}"
        )
    }
}
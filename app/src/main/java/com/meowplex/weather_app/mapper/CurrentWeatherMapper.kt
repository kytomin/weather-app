package com.meowplex.weather_app.mapper

import com.meowplex.weather_app.dto.forecast.ForecastWeatherResponse
import com.meowplex.weather_app.model.CurrentWeatherModel
import com.meowplex.weather_app.model.TemperatureModel

class CurrentWeatherMapper : Mapper<ForecastWeatherResponse, CurrentWeatherModel> {
    override fun transform(data: ForecastWeatherResponse): CurrentWeatherModel {
        return CurrentWeatherModel(
            city = data.location.name,
            temp = TemperatureModel(celsius = data.current.tempC, fahrenheit = data.current.tempF),
            maxTemp = TemperatureModel(
                celsius = data.forecast.days[0].day.maxtempC,
                fahrenheit = data.forecast.days[0].day.maxtempF
            ),
            minTemp = TemperatureModel(
                celsius = data.forecast.days[0].day.mintempC,
                fahrenheit = data.forecast.days[0].day.mintempF
            ),
            comment = data.current.condition.text,
        )
    }
}
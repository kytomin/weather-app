package com.meowplex.weather_app.mapper

import com.meowplex.weather_app.dto.forecast.ForecastWeatherResponse
import com.meowplex.weather_app.model.ForecastWeatherModel

class ForecastWeatherMapper : Mapper<ForecastWeatherResponse, ForecastWeatherModel> {
    override fun transform(data: ForecastWeatherResponse): ForecastWeatherModel {
        return ForecastWeatherModel(
            location = LocationMapper().transform(data.location),
            current = CurrentWeatherMapper().transform(data),
            daily = DailyWeatherMapper().transform(data),
            hourly = HourlyWeatherMapper().transform(data.forecast.days[0].hour),
            aqiModel = data.current.airQuality?.let { AqiMapper().transform(it) },
            astroModel = AstroMapper().transform(data),
            helpfulInfoModel = HelpfulInfoMapper().transform(data.current)
        )
    }
}
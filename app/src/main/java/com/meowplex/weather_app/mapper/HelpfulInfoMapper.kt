package com.meowplex.weather_app.mapper

import com.meowplex.weather_app.dto.current.CurrentDTO
import com.meowplex.weather_app.ext.toFormattedString
import com.meowplex.weather_app.model.HelpfulInfoModel

class HelpfulInfoMapper : Mapper<CurrentDTO, HelpfulInfoModel> {
    override fun transform(data: CurrentDTO): HelpfulInfoModel {
        return HelpfulInfoModel(
            humidity = "${data.humidity}%",
            feelsLike = data.feelslikeC.toFormattedString(),
            windSpeed = data.windKph.toFormattedString() + " km/h",
            windDir = data.windDir,
            uv = data.uv.toFormattedString()
        )
    }
}
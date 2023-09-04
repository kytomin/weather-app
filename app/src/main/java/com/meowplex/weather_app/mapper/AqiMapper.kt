package com.meowplex.weather_app.mapper

import com.meowplex.weather_app.R
import com.meowplex.weather_app.dto.AqiDTO
import com.meowplex.weather_app.ext.toFormattedString
import com.meowplex.weather_app.model.AqiModel

class AqiMapper : Mapper<AqiDTO, AqiModel> {
    override fun transform(data: AqiDTO): AqiModel {
        return AqiModel(
            comment = when (data.gbDefraIndex) {
                (1) -> R.string.low
                (2) -> R.string.low
                (3) -> R.string.low
                (4) -> R.string.moderate
                (5) -> R.string.moderate
                (6) -> R.string.moderate
                (7) -> R.string.high
                (8) -> R.string.high
                (9) -> R.string.high
                (10) -> R.string.very_high
                else -> R.string.low
            },
            co = data.co.toFormattedString() + " μg/m³",
            no2 = data.no2.toFormattedString() + " μg/m³",
            o3 = data.o3.toFormattedString() + " μg/m³",
            so2 = data.so2.toFormattedString() + " μg/m³"
        )
    }
}
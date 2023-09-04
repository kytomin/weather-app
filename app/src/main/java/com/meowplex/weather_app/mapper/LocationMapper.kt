package com.meowplex.weather_app.mapper

import com.meowplex.weather_app.dto.LocationDTO
import com.meowplex.weather_app.model.LocationModel
import java.time.LocalDateTime
import java.time.ZoneOffset

class LocationMapper : Mapper<LocationDTO, LocationModel> {
    override fun transform(data: LocationDTO): LocationModel {
        return LocationModel(
            city = data.name,
            region = data.region,
            country = data.country,
            latitude = data.lat,
            longitude = data.lon,
            date = LocalDateTime.ofEpochSecond(data.localtimeEpoch, 0, ZoneOffset.UTC)
        )
    }
}
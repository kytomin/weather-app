package com.meowplex.weather_app.mapper

import com.meowplex.weather_app.dto.SearchLocationDTO
import com.meowplex.weather_app.model.SearchLocationModel

class SearchLocationMapper : Mapper<SearchLocationDTO, SearchLocationModel> {
    override fun transform(data: SearchLocationDTO): SearchLocationModel {
        return SearchLocationModel(
            city = data.name,
            region = data.region,
            country = data.country,
            latitude = data.lat,
            longitude = data.lon
        )
    }
}
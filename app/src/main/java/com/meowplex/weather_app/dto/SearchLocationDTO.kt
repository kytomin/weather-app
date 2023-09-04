package com.meowplex.weather_app.dto

import com.google.gson.annotations.SerializedName

data class SearchLocationDTO(
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
)
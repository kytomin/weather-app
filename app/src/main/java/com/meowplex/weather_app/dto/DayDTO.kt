package com.meowplex.weather_app.dto

import com.google.gson.annotations.SerializedName

data class DayDTO(
    @SerializedName("maxtemp_c") val maxtempC: Double,
    @SerializedName("maxtemp_f") val maxtempF: Double,
    @SerializedName("mintemp_c") val mintempC: Double,
    @SerializedName("mintemp_f") val mintempF: Double,
    @SerializedName("avgtemp_c") val avgtempC: Double,
    @SerializedName("avgtemp_f") val avgtempF: Double,
    @SerializedName("maxwind_mph") val maxwindMph: Double,
    @SerializedName("maxwind_kph") val maxwindKph: Double,
    @SerializedName("totalprecip_mm") val totalprecipMm: Double,
    @SerializedName("totalprecip_in") val totalprecipIn: Double,
    @SerializedName("avgvis_km") val avgvisKm: Double,
    @SerializedName("avgvis_miles") val avgvisMiles: Double,
    @SerializedName("avghumidity") val avghumidity: Int,
    @SerializedName("condition") val condition: ConditionDTO,
    @SerializedName("uv") val uv: Double,
    @SerializedName("air_quality") val airQuality: AqiDTO?
)
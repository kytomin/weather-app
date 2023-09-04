package com.meowplex.weather_app.dto

import com.google.gson.annotations.SerializedName

data class ConditionDTO(
    @SerializedName("text") val text: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("code") val code: Int,
)
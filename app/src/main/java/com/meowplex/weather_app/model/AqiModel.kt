package com.meowplex.weather_app.model

import androidx.annotation.StringRes

data class AqiModel(
    val co: String,
    val o3: String,
    val no2: String,
    val so2: String,
    @StringRes val comment: Int
)
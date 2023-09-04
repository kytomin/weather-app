package com.meowplex.weather_app.model

import androidx.annotation.StringRes
import com.meowplex.weather_app.R

enum class TemperatureUnits {
    Celsius,
    Fahrenheit;

    @StringRes
    fun getNameResource(): Int {
        return when (this) {
            Celsius -> R.string.celsius
            Fahrenheit -> R.string.fahrenheit
        }
    }
}


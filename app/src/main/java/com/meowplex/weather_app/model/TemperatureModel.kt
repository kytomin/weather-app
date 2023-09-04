package com.meowplex.weather_app.model

import com.meowplex.weather_app.ext.toFormattedString

class TemperatureModel(private val celsius: Double, private val fahrenheit: Double) {

    fun getValue(tempUnit: TemperatureUnits = TemperatureUnits.Celsius): String {
        return when (tempUnit) {
            TemperatureUnits.Celsius -> celsius.toFormattedString() + "℃"
            TemperatureUnits.Fahrenheit -> fahrenheit.toFormattedString() + "℉"
        }
    }

}
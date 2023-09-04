package com.meowplex.weather_app.database.converters

import android.location.Location
import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromLocation(value: Location?): String {
        if (value == null)
            return ""
        return "${value.latitude};${value.longitude}"
    }

    @TypeConverter
    fun toLocation(value: String): Location? {
        if (value.isEmpty())
            return null

        val index = value.indexOf(';')

        val location = Location("")
        location.latitude = value.substring(0, index).toDouble()
        location.longitude = value.substring(index + 1).toDouble()
        return location
    }

}
package com.meowplex.weather_app.repository

import android.content.SharedPreferences
import android.location.Location
import com.meowplex.weather_app.model.TemperatureUnits
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SharedPrefsRepository @Inject constructor(private val sharedPrefs: SharedPreferences) {

    private val tempUnitName = "temp_unit"
    private val longitudeName = "longitude"
    private val latitudeName = "latitude"

    fun setLocation(l: Location) {
        sharedPrefs.edit()
            .putString(latitudeName, "${l.latitude}")
            .putString(longitudeName, "${l.longitude}")
            .apply()
    }

    fun getLocation(): Location? {
        var location: Location? = null
        val latitude = sharedPrefs.getString(latitudeName, "")
        val longitude = sharedPrefs.getString(longitudeName, "")
        if (!latitude.isNullOrEmpty() && !longitude.isNullOrEmpty()) {
            location = Location("")
            location.latitude = latitude.toDouble()
            location.longitude = longitude.toDouble()
        }
        return location
    }

    fun getTempUnit(): TemperatureUnits? {
        val tempUnitStr = sharedPrefs.getString(tempUnitName, "") ?: ""
        return try {
            TemperatureUnits.valueOf(tempUnitStr)
        } catch (_: Exception) {
            null
        }
    }

    fun setTempUnit(value: TemperatureUnits) {
        sharedPrefs.edit()
            .putString(tempUnitName, value.name).apply()
    }

}
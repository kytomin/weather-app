package com.meowplex.weather_app

import android.location.Location

object Constants {
    const val OK_HTTP_CACHE_SIZE: Long = 10L * 1024L * 1024L

    const val databaseName = "app"
    const val tableName = "cities"

    val defaultLocation = Location("").apply {
        this.latitude = 55.8
        this.longitude = 37.9
    }
}
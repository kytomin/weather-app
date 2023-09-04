package com.meowplex.weather_app.navigation

object Routes {
    fun MAIN(
        latitude: Double? = null,
        longitude: Double? = null,
        isPrimaryCity: Boolean = false
    ): String {
        return if (latitude == null || longitude == null)
            "Main/{latitude}/{longitude}/{isPrimaryCity}"
        else
            "Main/${latitude}/${longitude}/${isPrimaryCity}"
    }

    const val MANAGE_CITIES = "ManageCities"
    fun ADD_CITY(primaryCityExists: Boolean? = null): String {
        return if (primaryCityExists == null) "AddCity/{primaryCityExists}"
        else "AddCity/${primaryCityExists}"
    }

    const val SETTINGS = "Settings"

    const val ABOUT = "About"
}
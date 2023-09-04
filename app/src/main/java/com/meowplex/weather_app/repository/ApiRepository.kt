package com.meowplex.weather_app.repository

import android.location.Location
import com.meowplex.weather_app.mapper.ForecastWeatherMapper
import com.meowplex.weather_app.mapper.SearchLocationMapper
import com.meowplex.weather_app.mapper.WeatherPreviewMapper
import com.meowplex.weather_app.model.ForecastWeatherModel
import com.meowplex.weather_app.model.SearchLocationModel
import com.meowplex.weather_app.model.WeatherPreviewModel
import com.meowplex.weather_app.service.ApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(private val weatherApiService: ApiService) {

    fun getWeatherPreview(location: String): Single<WeatherPreviewModel> {
        return weatherApiService.getCurrentWeather(location).map {
            WeatherPreviewMapper().transform(it)
        }
    }

    fun getWeatherPreview(location: Location): Single<WeatherPreviewModel> {
        val q = "${location.latitude},${location.longitude}"
        return getWeatherPreview(q)
    }

    fun getForecastWeather(
        location: String,
        days: Int = 7,
        getAqi: Boolean = false
    ): Single<ForecastWeatherModel> {
        val aqi: String = if (getAqi) "yes" else "no"
        return weatherApiService.getForecastWeather(location, days, aqi).map {
            ForecastWeatherMapper().transform(it)
        }
    }

    fun getForecastWeather(
        location: Location,
        days: Int = 7,
        getAqi: Boolean = false
    ): Single<ForecastWeatherModel> {
        val q = "${location.latitude},${location.longitude}"
        return getForecastWeather(q, days, getAqi)
    }

    fun searchCities(
        name: String
    ): Single<List<SearchLocationModel>> {
        return weatherApiService.searchCities(name).map {
            it.map { city ->
                SearchLocationMapper().transform(city)
            }
        }
    }


    fun searchCities(
        location: Location
    ): Single<List<SearchLocationModel>> {
        return weatherApiService.searchCities("${location.latitude},${location.longitude}").map {
            it.map { city ->
                SearchLocationMapper().transform(city)
            }
        }
    }

}
package com.meowplex.weather_app.service

import com.meowplex.weather_app.dto.SearchLocationDTO
import com.meowplex.weather_app.dto.current.CurrentWeatherResponse
import com.meowplex.weather_app.dto.forecast.ForecastWeatherResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("current.json")
    fun getCurrentWeather(@Query("q") q: String): Single<CurrentWeatherResponse>

    @GET("forecast.json")
    fun getForecastWeather(
        @Query("q") q: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String
    ): Single<ForecastWeatherResponse>

    @GET("search.json")
    fun searchCities(
        @Query("q") q: String
    ): Single<List<SearchLocationDTO>>

}
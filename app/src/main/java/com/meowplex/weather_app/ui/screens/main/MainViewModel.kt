package com.meowplex.weather_app.ui.screens.main

import android.location.Location
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.meowplex.weather_app.database.entities.CityEntity
import com.meowplex.weather_app.model.ForecastWeatherModel
import com.meowplex.weather_app.model.TemperatureUnits
import com.meowplex.weather_app.navigation.Router
import com.meowplex.weather_app.navigation.Routes
import com.meowplex.weather_app.repository.ApiRepository
import com.meowplex.weather_app.repository.DatabaseRepository
import com.meowplex.weather_app.repository.SharedPrefsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val sharedPrefsRepository: SharedPrefsRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    var uiState = mutableStateOf(MainState())

    private fun load(location: Location) {
        val single = apiRepository.getForecastWeather(
            location = location,
            getAqi = true,
            days = 7
        )
        single.subscribeOn(Schedulers.io()).subscribe(apiObserver)
    }

    fun load(latitude: Double, longitude: Double, isPrimaryCity: Boolean) {
        val location = Location("")
        location.latitude = latitude
        location.longitude = longitude
        uiState.value = uiState.value.copy(isPrimaryCity = isPrimaryCity)
        load(location)
    }

    fun load(router: Router) {
        databaseRepository.getCities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(dbObserver(router))
    }

    fun onRetry() {
        if (uiState.value.isPrimaryCity) {
            databaseRepository.getCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dbObserver())
        } else {
            val lat = uiState.value.location!!.latitude
            val long = uiState.value.location!!.longitude
            load(lat, long, false)
        }
    }

    fun onSettings(router: Router) {
        router.navigate(Routes.SETTINGS)
    }

    fun onManageCities(router: Router) {
        router.navigate(Routes.MANAGE_CITIES)
    }

    private fun dbObserver(router: Router? = null) = object : SingleObserver<List<CityEntity>> {
        override fun onSubscribe(d: Disposable) {
            uiState.value = MainState(isLoading = true, isPrimaryCity = uiState.value.isPrimaryCity)
        }

        override fun onError(e: Throwable) {
            uiState.value = MainState(
                error = "Database ${e.message}",
                isLoading = false,
                isPrimaryCity = uiState.value.isPrimaryCity
            )
        }

        override fun onSuccess(cities: List<CityEntity>) {
            var primaryCity: CityEntity? = null
            cities.forEach { city ->
                if (city.isPrimary) {
                    primaryCity = city
                    uiState.value = uiState.value.copy(isPrimaryCity = true)
                }
            }
            if (primaryCity != null) {
                load(primaryCity!!.location)
            } else if (cities.isNotEmpty()) {
                load(cities.first().location)
            } else {
                router?.clearAndNavigate(Routes.MANAGE_CITIES)
            }

        }

    }

    private val apiObserver = object : SingleObserver<ForecastWeatherModel> {

        override fun onSubscribe(d: Disposable) {
            uiState.value = MainState(
                isLoading = true,
                isPrimaryCity = uiState.value.isPrimaryCity
            )
        }

        override fun onError(e: Throwable) {
            Log.e("MainViewModel", "${e.message}")

            val error: String = when (e) {
                is IOException -> {
                    "NETWORK"
                }
                is HttpException -> {
                    when (e.code()) {
                        400 -> "INVALID REQUEST"
                        401 -> "INVALID API KEY"
                        403 -> "API KEY DISABLED"
                        else -> "UNKNOWN HOST ERROR"
                    }
                }
                else -> "${e.message}"
            }

            uiState.value = MainState(
                error = error,
                isLoading = false,
                isPrimaryCity = uiState.value.isPrimaryCity
            )
        }

        override fun onSuccess(res: ForecastWeatherModel) {
            uiState.value = MainState(
                location = res.location,
                currentWeather = res.current,
                dailyWeather = res.daily,
                hourlyWeather = res.hourly,
                helpfulInfo = res.helpfulInfoModel,
                aqi = res.aqiModel,
                astro = res.astroModel,
                isLoading = false,
                tempUnit = sharedPrefsRepository.getTempUnit() ?: TemperatureUnits.Celsius,
                isPrimaryCity = uiState.value.isPrimaryCity
            )
        }
    }
}
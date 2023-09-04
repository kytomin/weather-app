package com.meowplex.weather_app.ui.screens.add_city

import android.location.Location
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.meowplex.weather_app.database.entities.CityEntity
import com.meowplex.weather_app.model.SearchLocationModel
import com.meowplex.weather_app.navigation.Router
import com.meowplex.weather_app.navigation.Routes
import com.meowplex.weather_app.repository.ApiRepository
import com.meowplex.weather_app.repository.DatabaseRepository
import com.meowplex.weather_app.service.LocService
import com.meowplex.weather_app.service.MyLocationListener
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class AddCityViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val apiRepository: ApiRepository,
    private val locService: LocService
) : ViewModel() {

    var uiState = mutableStateOf(AddCityState())


    fun onInputChange(str: String) {
        uiState.value = uiState.value.copy(inputValue = str)
        val city = str.lowercase().trim()

        apiRepository.searchCities(city)
            .subscribeOn(Schedulers.io()).subscribe(searchObserver)


    }

    fun onAddCity(city: SearchLocationModel, router: Router, isPrimary: Boolean = false) {
        val location = Location("")
        location.latitude = city.latitude
        location.longitude = city.longitude

        databaseRepository.addCity(
            city = CityEntity(
                name = city.city,
                location = location,
                isPrimary = isPrimary
            )
        ).subscribeOn(Schedulers.io()).subscribe()

        router.navigateAndPopUp(Routes.MANAGE_CITIES, Routes.ADD_CITY())
    }

    fun onCurrentLocation(router: Router) {
        if (uiState.value.currentCity == null) {
            locService.startLocationUpdates(locationObserver)
            uiState.value = uiState.value.copy(loadingCurrentCity = true)
        } else {
            locService.stopLocationUpdates(locationObserver)
            onAddCity(uiState.value.currentCity!!, router, true)
        }
    }

    private val locationObserver = object : MyLocationListener {
        override fun onLocationChanged(location: Location) {
            apiRepository.searchCities(location)
                .doOnError {
                    uiState.value =
                        uiState.value.copy(currentCity = null, loadingCurrentCity = false)
                }
                .doOnSuccess { cities ->
                    val city = cities.first()
                    uiState.value =
                        uiState.value.copy(currentCity = city, loadingCurrentCity = false)
                }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
        }

    }

    private val searchObserver = object : SingleObserver<List<SearchLocationModel>> {
        override fun onSubscribe(d: Disposable) {
            Log.d("AddCityViewModel", "subscribe")
        }

        override fun onSuccess(data: List<SearchLocationModel>) {
            uiState.value = uiState.value.copy(cities = data)
        }

        override fun onError(e: Throwable) {
            Log.e("AddCityViewModel", "${e.message}")
        }
    }

}
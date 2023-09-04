package com.meowplex.weather_app.ui.screens.manage_cities

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.meowplex.weather_app.model.CityModel
import com.meowplex.weather_app.model.TemperatureUnits
import com.meowplex.weather_app.navigation.Router
import com.meowplex.weather_app.navigation.Routes
import com.meowplex.weather_app.repository.ApiRepository
import com.meowplex.weather_app.repository.DatabaseRepository
import com.meowplex.weather_app.repository.SharedPrefsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ManageCitiesViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val apiRepository: ApiRepository,
    private val sharedPrefsRepository: SharedPrefsRepository
) : ViewModel() {

    var uiState = mutableStateOf(ManageCitiesState())


    fun load() {

        val observable = databaseRepository.getCities()
            .flattenAsObservable { it }
            .flatMapSingle { city ->
                apiRepository.getWeatherPreview(
                    location = city.location,
                )
                    .map { res ->
                        CityModel(
                            id = city.uid,
                            latitude = city.location.latitude,
                            longitude = city.location.longitude,
                            weather = res,
                            isPrimary = city.isPrimary
                        )
                    }
            }.sorted { city1, city2 ->
                if (city1.isPrimary)
                    return@sorted -1
                if (city2.isPrimary)
                    return@sorted 1
                if (city1.id < city2.id)
                    return@sorted -1
                return@sorted 0
            }
            .toList()
        observable.subscribeOn(Schedulers.io()).subscribe(apiObserver)
    }


    fun onAddCity(router: Router) {
        var primaryCityExists = false
        uiState.value.cities.forEach { city ->
            if (city.isPrimary)
                primaryCityExists = true
        }
        router.navigate(Routes.ADD_CITY(primaryCityExists = primaryCityExists))
    }

    fun onSelectCity(router: Router, city: CityModel) {
        router.clearAndNavigate(Routes.MAIN(city.latitude, city.longitude, city.isPrimary))
    }

    fun onRemoveCity(router: Router, id: Long, index: Int) {
        databaseRepository.deleteCityById(id).subscribeOn(Schedulers.io()).subscribe()

        val newCities = uiState.value.cities.toMutableList()
        newCities.removeAt(index)
        uiState.value = uiState.value.copy(
            cities = newCities.toList(),
            tempUnit = sharedPrefsRepository.getTempUnit() ?: TemperatureUnits.Celsius
        )
        if (newCities.isEmpty()) {
            router.clear(Routes.MAIN())
        }
    }


    private val apiObserver =
        object : SingleObserver<List<CityModel>> {
            override fun onSubscribe(d: Disposable) {}

            override fun onError(e: Throwable) {
                Log.e("ManageCitiesViewModel", "${e.message}")
            }

            override fun onSuccess(data: List<CityModel>) {
                uiState.value = uiState.value.copy(
                    cities = data,
                    tempUnit = sharedPrefsRepository.getTempUnit() ?: TemperatureUnits.Celsius
                )
            }

        }

}
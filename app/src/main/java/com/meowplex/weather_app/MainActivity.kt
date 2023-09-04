package com.meowplex.weather_app

import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import com.meowplex.weather_app.database.entities.CityEntity
import com.meowplex.weather_app.repository.DatabaseRepository
import com.meowplex.weather_app.service.LocService
import com.meowplex.weather_app.service.MyLocationListener
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var locService: LocService

    @Inject
    lateinit var databaseRepository: DatabaseRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { WeatherApp() }

        locationPermissionRequest.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    override fun onResume() {
        super.onResume()
        locService.startLocationUpdates(listener)
    }

    override fun onPause() {
        super.onPause()
        locService.stopLocationUpdates(listener)
    }

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->

        permissions.getOrDefault(android.Manifest.permission.ACCESS_FINE_LOCATION, false)
        permissions.getOrDefault(android.Manifest.permission.ACCESS_COARSE_LOCATION, false)

    }

    private val listener = object : MyLocationListener {
        override fun onLocationChanged(location: Location) {
            databaseRepository.getCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { cities ->
                    var primaryCity: CityEntity? = null
                    cities.forEach { city ->
                        if (city.isPrimary) {
                            primaryCity = city
                        }
                    }
                    if (primaryCity != null) {
                        primaryCity = primaryCity!!.copy(location = location)
                        databaseRepository.updateCity(primaryCity!!).subscribeOn(Schedulers.io())
                            .subscribe()
                    }
                }.subscribe()
        }
    }
}